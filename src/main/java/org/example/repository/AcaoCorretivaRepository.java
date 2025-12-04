package org.example.repository;

import org.example.database.Prepare;
import org.example.model.AcaoCorretiva;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AcaoCorretivaRepository {
    private String query;

    public AcaoCorretiva registrarConclusaoDeAcao(AcaoCorretiva acao) throws SQLException {
        query = """
                INSERT INTO AcaoCorretiva
                (falhaId, dataHoraInicio, dataHoraFim, responsavel, descricaoAcao)
                VALUES (?,?,?,?,?)
                """;
        try(PreparedStatement stmt = Prepare.conn(query)){
            stmt.setLong(1, acao.getFalhaId());
            stmt.setTimestamp(2, Timestamp.valueOf(acao.getDataHoraInicio()));
            stmt.setTimestamp(3, Timestamp.valueOf(acao.getDataHoraFim()));
            stmt.setString(4, acao.getResponsavel());
            stmt.setString(5, acao.getDescricaoArea());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                acao.setId(rs.getLong(1));
            }
        }
        return acao;
    }

    public List<String> descricoes(Long falhaId) throws SQLException{
        List<String> descricoes = new ArrayList<>();
        query = """
                SELECT descricaoAcao
                FROM AcaoCorretiva
                WHERE falhaId = ?
                """;
        try(PreparedStatement stmt = Prepare.conn(query)){
            stmt.setLong(1, falhaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                descricoes.add(rs.getString("descricaoAcao"));
            }
        }
        return descricoes;
    }
}
