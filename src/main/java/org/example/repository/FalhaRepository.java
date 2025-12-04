package org.example.repository;

import org.example.database.Prepare;
import org.example.model.Falha;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FalhaRepository {
    private String query;

    public Falha registrarNovaFalha(Falha falha) throws SQLException{
        query = """
                INSERT INTO  Falha
                (equipamentoId, dataHoraOcorrencia, descricao, criticidade, status, tempoParadaHoras)
                VALUES (?,?,?,?,?,?)
                """;
        try(PreparedStatement stmt = Prepare.conn(query)){
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
        try (PreparedStatement stmt = Prepare.conn(query)){
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
        try (PreparedStatement stmt = Prepare.conn(query)){
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return (rs.next()) ? new Falha(
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
        try(PreparedStatement stmt = Prepare.conn(query)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
