package org.example.repository;

import org.example.database.Conexao;
import org.example.dto.EquipamentoContagemFalhasDTO;
import org.example.model.Equipamento;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EquipamentoRepository {
    private String query;

    public Equipamento criarEquipamento(Equipamento equipamento) throws SQLException{
        query = """
                INSERT INTO Equipamento
                (nome, numeroDeSerie, areaSetor, statusOperacional)
                VALUES (?,?,?,?)
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getNumeroDeSerie());
            stmt.setString(3, equipamento.getAreaSetor());
            stmt.setString(4, equipamento.getStatusOperacional());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                equipamento.setId(rs.getLong(1));
            }
        }
        return equipamento;
    }

    public Equipamento buscarEquipamentoPorId(Long id) throws SQLException{
        query = """
                SELECT id,
                       nome,
                       numeroDeSerie,
                       areaSetor,
                       statusOperacional
                FROM Equipamento
                WHERE id = ?
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Equipamento(rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("numeroDeSerie"),
                        rs.getString("areaSetor"),
                        rs.getString("statusOperacional"));
            }
        }
        return null;
    }

    public boolean equipamentoExiste(Long id) throws SQLException{
        query = """
                SELECT id
                FROM Equipamento
                WHERE id = ?
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return (rs.next()) ? true : false;
        }
    }

    public void atualizarStatus(Long id, String status) throws SQLException{
        query = """
                UPDATE Equipamento
                SET statusOperacional = ?
                WHERE id = ?
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, status);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }

    public List<Equipamento> buscarEquipamentosSemFalhasPorPeriodo(LocalDate dataInicio, LocalDate dataFim) throws SQLException{
        List<Equipamento> equipamentos = new ArrayList<>();
        query = """
                SELECT e.id,
                       e.nome,
                       e.numeroDeSerie,
                       e.areaSetor,
                       e.statusOperacional,
                       COUNT(f.id) AS total
                FROM Equipamento e 
                LEFT JOIN Falha f ON e.id = f.equipamentoId
                AND f.dataHoraOcorrencia BETWEEN ? AND ?
                GROUP BY e.id,
                       e.nome
                HAVING total = 0
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setDate(1, Date.valueOf(dataInicio));
            stmt.setDate(2, Date.valueOf(dataFim));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                equipamentos.add(new Equipamento(
                        rs.getLong("e.id"),
                        rs.getString("e.nome"),
                        rs.getString("e.numeroDeSerie"),
                        rs.getString("e.areaSetor"),
                        rs.getString("e.statusOperacional")
                ));
            }
        }
        return equipamentos;
    }

    public List<EquipamentoContagemFalhasDTO> gerarRelatorioManutencaoPreventiva(int contagemMinimaFalhas) throws SQLException{
        List<EquipamentoContagemFalhasDTO> equipamentos = new ArrayList<>();
        query = """
                SELECT e.id,
                       e.nome,
                       COUNT(f.id) as total
                FROM Equipamento e
                JOIN Falha f ON e.id = f.equipamentoId
                GROUP BY e.id, e.nome
                HAVING total >= ?
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1, contagemMinimaFalhas);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                equipamentos.add(new EquipamentoContagemFalhasDTO(
                        rs.getLong("e.id"),
                        rs.getString("e.nome"),
                        rs.getInt("total")
                ));
            }
        }
        return equipamentos;
    }
}
