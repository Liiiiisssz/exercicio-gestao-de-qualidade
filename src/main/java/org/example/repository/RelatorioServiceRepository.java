package org.example.repository;

import org.example.dto.EquipamentoContagemFalhasDTO;
import org.example.dto.FalhaDetalhadaDTO;
import org.example.dto.RelatorioParadaDTO;
import org.example.model.Equipamento;
import org.example.database.Conexao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RelatorioServiceRepository {
    private String query;

    public List<RelatorioParadaDTO> gerarRelatorioTempoParada() throws SQLException {
        List<RelatorioParadaDTO> relatorio = new ArrayList<>();
        query = """
                SELECT f.id,
                       f.descricao,
                       f.criticidade,
                       f.status,
                       e.id,
                       e.nome,
                       e.numeroDeSerie,
                       e.areaSetor,
                       f.dataHoraOcorrencia,
                       a.dataHoraInicio,
                       a.dataHoraFim,
                       a.responsavel,
                       a.descricaoAcao,
                       f.tempoParadaHoras
                FROM Falha f
                JOIN Equipamento e ON f.equipamentoId = e.id
                LEFT JOIN AcaoCorretiva a ON a.falhaId = f.id
                """;
        try(Connection conn = Conexao.conectar();
            ResultSet rs = conn.createStatement().executeQuery(query)){
            while(rs.next()){
                relatorio.add(new RelatorioParadaDTO(
                        rs.getLong("f.id"),
                        rs.getString("f.descricao"),
                        rs.getString("f.criticidade"),
                        rs.getString("f.status"),
                        rs.getLong("e.id"),
                        rs.getString("e.nome"),
                        rs.getString("e.numeroDeSerie"),
                        rs.getString("e.areaSetor"),
                        rs.getTimestamp("f.dataHoraOcorrencia").toLocalDateTime(),
                        rs.getTimestamp("a.dataHoraInicio").toLocalDateTime(),
                        rs.getTimestamp("a.dataHoraFim").toLocalDateTime(),
                        rs.getString("a.responsavel"),
                        rs.getString("a.descricaoAcao"),
                        rs.getBigDecimal("f.tempoParadaHoras")
                ));
            }
        }
        return relatorio;
    }

    public List<Equipamento> buscarEquipamentosSemFalhasPorPeriodo(LocalDateTime inicio, LocalDateTime fim) throws SQLException{
        List<Equipamento> equipamentos = new ArrayList<>();
        query = """
                SELECT e.id,
                       e.nome,
                       e.numeroDeSerie,
                       e.areaSetor,
                       e.statusOperacional,
                       COUNT(f.id) AS total
                FROM Equipamento e
                LEFT JOIN Falha f ON f.equipamentoId = e.id
                AND f.dataHoraOcorrencia BETWEEN ? AND ?
                GROUP BY e.id, e.nome
                HAVING total = 0
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setTimestamp(1, Timestamp.valueOf(inicio));
            stmt.setTimestamp(2, Timestamp.valueOf(fim));
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
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

    public Optional<FalhaDetalhadaDTO> buscarDetalhesCompletosFalha(Long falhaId) throws SQLException{

        query = """
                SELECT f.id,
                       f.dataHoraOcorrencia,
                       f.descricao,
                       f.criticidade,
                       f.status,
                       f.tempoParadaHoras,
                       e.id,
                       e.nome,
                       e.numeroDeSerie,
                       e.areaSetor,
                       e.statusOperacional,
                       a.id,
                       a.dataHoraInicio,
                       a.dataHoraFim,
                       a.responsavel,
                       a.descricaoAcao
                FROM Falha f
                LEFT JOIN Equipamento e ON f.equipamentoId = e.id
                LEFT JOIN AcaoCorretiva a ON f.id = a.falhaId
                WHERE f.id = ?
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1, falhaId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                FalhaDetalhadaDTO falha = new FalhaDetalhadaDTO(
                        rs.getLong("f.id"),
                        rs.getTimestamp("f.dataHoraOcorrencia").toLocalDateTime(),
                        rs.getString("f.descricao"),
                        rs.getString("f.criticidade"),
                        rs.getString("f.status"),
                        rs.getBigDecimal("f.tempoParadaHoras"),
                        rs.getLong("e.id"),
                        rs.getString("e.nome"),
                        rs.getString("e.numeroDeSerie"),
                        rs.getString("e.areaSetor"),
                        rs.getString("e.statusOperacional"),
                        rs.getLong("idAcao"),
                        rs.getTimestamp("a.dataHoraInicio").toLocalDateTime(),
                        rs.getTimestamp("a.dataHoraFim").toLocalDateTime(),
                        rs.getString("a.responsavel"),
                        rs.getString("a.descricaoAcao")
                        );
                return Optional.of(falha);
            }
        }
        return Optional.empty();
    }

    public List<EquipamentoContagemFalhasDTO> gerarRelatorioManutencaoPreventiva(int contagem) throws SQLException{
        List<EquipamentoContagemFalhasDTO> equipamentos = new ArrayList<>();
        query = """
                SELECT e.id,
                       e.nome,
                       e.numeroDeSerie,
                       e.areaSetor,
                       e.statusOperacional,
                       COUNT(f.id) AS total
                FROM Equipamento e
                JOIN Falha f ON f.equipamentoId = e.id
                GROUP BY e.nome
                HAVING COUNT(f.id) >= ?
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, contagem);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                equipamentos.add(new EquipamentoContagemFalhasDTO(
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
}
