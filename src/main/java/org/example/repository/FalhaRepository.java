package org.example.repository;

import org.example.model.Falha;
import org.example.database.Conexao;

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
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            stmt.setLong(1, falha.getEquipamentoId());
            stmt.setTimestamp(2, Timestamp.valueOf(falha.getDataOcorrencia()));
            stmt.setString(3, falha.getDescricao());
            stmt.setString(4, falha.getCriticidade());
            stmt.setString(5, falha.getStatus());
            stmt.setBigDecimal(6, falha.getTempoParada());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
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

    public Falha buscarFalhaId(Long id) throws SQLException{
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
            while (rs.next()) {
                return new Falha(
                        rs.getLong("id"),
                        rs.getLong("equipamentoId"),
                        rs.getTimestamp("dataHoraOcorrencia").toLocalDateTime(),
                        rs.getString("descricao"),
                        rs.getString("criticidade"),
                        rs.getString("status"),
                        rs.getBigDecimal("tempoParadaHoras")
                );
            }
        }
        return null;
    }

    public void atualizarStatus(long id) throws SQLException{
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
}
