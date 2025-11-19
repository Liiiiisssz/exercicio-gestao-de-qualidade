package org.example.service.relatorioservice;

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

public class RelatorioServiceImpl implements RelatorioService{
    RelatorioServiceRepository rep = new RelatorioServiceRepository();

    @Override
    public List<RelatorioParadaDTO> gerarRelatorioTempoParada() throws SQLException {
        return rep.gerarRelatorioTempoParada();
    }

    @Override
    public List<Equipamento> buscarEquipamentosSemFalhasPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) throws SQLException{
        return rep.buscarEquipamentosSemFalhasPorPeriodo(dataInicio, dataFim);
    }

    @Override
    public Optional<FalhaDetalhadaDTO> buscarDetalhesCompletosFalha(Long falhaId) throws SQLException{
        var repFalha = new FalhaRepository();
        if(repFalha.buscarFalhaId(falhaId) == null){
            throw new RuntimeException("ID da falha inválido!");
        }
        return rep.buscarDetalhesCompletosFalha(falhaId);
    }

    @Override
    public List<EquipamentoContagemFalhasDTO> gerarRelatorioManutencaoPreventiva(int contagemMinimaFalhas) throws SQLException{
        if(contagemMinimaFalhas < 1){
            throw new RuntimeException("Valor informado inválido");
        }
        return rep.gerarRelatorioManutencaoPreventiva(contagemMinimaFalhas);
    }
}
