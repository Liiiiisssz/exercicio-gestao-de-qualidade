package org.example.repository;

import org.example.model.Equipamento;
import org.example.database.Conexao;

import java.sql.*;

public class EquipamentoRepository {
    private String query;

    public Equipamento criarEquipamento(Equipamento equipamento) throws SQLException{
        query = """
                INSERT INTO Equipamento
                (nome, numeroDeSerie, areaSetor, statusOperacional)
                VALUES (?, ?, ?, ?)
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getNumeroDeSerie());
            stmt.setString(3, equipamento.getAreaSetor());
            stmt.setString(4, "OPERACIONAL");
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                equipamento.setId(rs.getLong(1));
            }
        }
        return equipamento;
    }

    public Equipamento buscarEquipamentoPorId(long id) throws SQLException{
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
            if(rs.next()){
                return new Equipamento(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("numeroDeSerie"),
                        rs.getString("areaSetor"),
                        rs.getString("statusOperacional"));
            }
        }
        return null;
    }

    public void atualizarStatus(long id, String status) throws SQLException{
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
}
