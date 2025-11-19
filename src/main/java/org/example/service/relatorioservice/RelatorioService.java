package org.example.service.relatorioservice;

import org.example.dto.EquipamentoContagemFalhasDTO;
import org.example.dto.FalhaDetalhadaDTO;
import org.example.dto.RelatorioParadaDTO;
import org.example.model.Equipamento;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RelatorioService {
    List<RelatorioParadaDTO> gerarRelatorioTempoParada() throws SQLException;

    List<Equipamento> buscarEquipamentosSemFalhasPorPeriodo(LocalDateTime dataInicio, LocalDateTime datafim)throws SQLException;

    Optional<FalhaDetalhadaDTO> buscarDetalhesCompletosFalha(Long falhaId) throws SQLException;

    List<EquipamentoContagemFalhasDTO> gerarRelatorioManutencaoPreventiva(int contagemMinimaFalhas) throws SQLException;
}
