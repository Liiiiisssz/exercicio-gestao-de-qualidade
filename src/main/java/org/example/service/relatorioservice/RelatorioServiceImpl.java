package org.example.service.relatorioservice;

import org.example.dto.EquipamentoContagemFalhasDTO;
import org.example.dto.FalhaDetalhadaDTO;
import org.example.dto.RelatorioParadaDTO;
import org.example.model.Equipamento;
import org.example.repository.AcaoCorretivaRepository;
import org.example.repository.EquipamentoRepository;
import org.example.repository.FalhaRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RelatorioServiceImpl implements RelatorioService{
    private FalhaRepository falhaRep = new FalhaRepository();
    private EquipamentoRepository eqpRep = new EquipamentoRepository();
    private AcaoCorretivaRepository acaoRep = new AcaoCorretivaRepository();

    @Override
    public List<RelatorioParadaDTO> gerarRelatorioTempoParada() throws SQLException {
        return falhaRep.gerarRelatorioTempoParada();
    }

    @Override
    public List<Equipamento> buscarEquipamentosSemFalhasPorPeriodo(LocalDate dataInicio, LocalDate datafim) throws SQLException {
        return eqpRep.buscarEquipamentosSemFalhasPorPeriodo(dataInicio, datafim);
    }

    @Override
    public Optional<FalhaDetalhadaDTO> buscarDetalhesCompletosFalha(long falhaId) throws SQLException {
        if(falhaRep.buscarFalhaId(falhaId) == null){
            throw new RuntimeException();
        }
        var falha = falhaRep.buscarFalhaId(falhaId);
        return Optional.of(new FalhaDetalhadaDTO(
                falha,
                eqpRep.buscarEquipamentoPorId(falha.getEquipamentoId()),
                new ArrayList<>(acaoRep.descricoes(falhaId))));
    }

    @Override
    public List<EquipamentoContagemFalhasDTO> gerarRelatorioManutencaoPreventiva(int contagemMinimaFalhas) throws SQLException {
        if(contagemMinimaFalhas < 1){
            throw new RuntimeException();
        }
        return eqpRep.gerarRelatorioManutencaoPreventiva(contagemMinimaFalhas);
    }
}
