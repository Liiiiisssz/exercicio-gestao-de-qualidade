package org.example.repository;

import org.example.database.Conexao;
import org.example.dto.EquipamentoContagemFalhasDTO;
import org.example.model.Falha;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FalhaRepository {
    private String query;

    public Falha registrarNovaFalha(Falha falha) throws SQLException{
        query = """
                INSERT INTO Falha
                (equipamentoId, dataHoraOcorrencia, descricao,
                criticidade, status, tempoParadaHoras)
                VALUES (?,?,?,?,?,?)
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            stmt.setLong(1, falha.getEquipamentoId());
            stmt.setTimestamp(2, Timestamp.valueOf(falha.getDataHoraOcorrencia()));
            stmt.setString(3, falha.getDescricao());
            stmt.setString(4, falha.getCriticidade());
            stmt.setString(5, falha.getStatus());
            stmt.setBigDecimal(6, falha.getTempoParadaHoras());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                falha.setId(rs.getLong(1));
            }
        }
        return falha;
    }

    public List<Falha> buscarFalhasCriticasAbertas() throws SQLException{
        List<Falha> falhas = new ArrayList<>();
        query = """
                SELECT id,
                       equipamentoId,
                       dataHoraOcorrencia,
                       descricao,
                       criticidade,
                       status,
                       tempoParadaHoras
                FROM Falha
                WHERE status = 'ABERTA'
                AND criticidade = 'CRITICA'
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                falhas.add(new Falha(
                        rs.getLong("id"),
                        rs.getLong("equipamentoId"),
                        rs.getTimestamp("dataHoraOcorrencia").toLocalDateTime(),
                        rs.getString("descricao"),
                        rs.getString("criticidade"),
                        rs.getString("status"),
                        rs.getBigDecimal("tempoParadaHoras")
                ));
            }
        }
        return falhas;
    }

    public Falha buscarFalhaPorId(Long id) throws SQLException{
        query = """
                SELECT id,
                       equipamentoId,
                       dataHoraOcorrencia,
                       descricao,
                       criticidade,
                       status,
                       tempoParadaHoras
                FROM Falha
                WHERE id = ?
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return (rs.next()) ?
                    new Falha(
                            rs.getLong("id"),
                            rs.getLong("equipamentoId"),
                            rs.getTimestamp("dataHoraOcorrencia").toLocalDateTime(),
                            rs.getString("descricao"),
                            rs.getString("criticidade"),
                            rs.getString("status"),
                            rs.getBigDecimal("tempoParadaHoras")
                    ) : null;
        }
    }

    public void atualizarStatus(Long id) throws SQLException{
        query = """
                UPDATE Falha
                SET status = 'RESOLVIDA'
                WHERE id = ?
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public List<EquipamentoContagemFalhasDTO> gerarRelatorioManutencaoPreventiva(int contagem) throws SQLException{
        List<EquipamentoContagemFalhasDTO> falhas = new ArrayList<>();
        query = """
                SELECT e.id,
                       e.nome,
                       COUNT(f.equipamentoId) AS total
                FROM Equipamento e
                JOIN Falha f ON e.id = f.equipamentoId
                GROUP BY e.id ,e.nome
                HAVING total >= ?
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, contagem);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                falhas.add(new EquipamentoContagemFalhasDTO(
                        rs.getLong("e.id"),
                        rs.getString("e.nome"),
                        rs.getInt("total")
                ));
            }
        }
        return falhas;
    }
}
