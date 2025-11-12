package org.example.service;

import org.example.dto.EquipamentoContagemFalhasDTO;
import org.example.dto.FalhaDetalhadaDTO;
import org.example.dto.RelatorioParadaDTO;
import org.example.model.Equipamento;
import org.example.repository.FalhaRepository;
import org.example.repository.RelatorioServiceRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class RelatorioService {
    RelatorioServiceRepository rep = new RelatorioServiceRepository();

    public List<RelatorioParadaDTO> gerarRelatorioTempoParada() throws SQLException {
        return rep.gerarRelatorioTempoParada();
    }

    public List<Equipamento> buscarEquipamentosSemFalhasPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) throws SQLException{
        return rep.buscarEquipamentosSemFalhasPorPeriodo(dataInicio, dataFim);
    }

    public Optional<FalhaDetalhadaDTO> buscarDetalhesCompletosFalha(Long falhaId) throws SQLException{
        var repFalha = new FalhaRepository();
        if(!repFalha.verificarId(falhaId)){
            throw new RuntimeException("ID da falha inválido!");
        }
        return rep.buscarDetalhesCompletosFalha(falhaId);
    }

    public List<EquipamentoContagemFalhasDTO> gerarRelatorioManutencaoPreventiva(int contagemMinimaFalhas) throws SQLException{
        if(contagemMinimaFalhas < 1){
            throw new RuntimeException("Valor informado inválido");
        }
        return rep.gerarRelatorioManutencaoPreventiva(contagemMinimaFalhas);
    }
}
