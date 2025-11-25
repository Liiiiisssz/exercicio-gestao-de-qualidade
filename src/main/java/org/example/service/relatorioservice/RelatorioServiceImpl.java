package org.example.service.relatorioservice;

import org.example.dto.EquipamentoContagemFalhasDTO;
import org.example.dto.FalhaDetalhadaDTO;
import org.example.dto.RelatorioParadaDTO;
import org.example.model.Equipamento;
import org.example.model.Falha;
import org.example.repository.AcaoCorretivaRepository;
import org.example.repository.EquipamentoRepository;
import org.example.repository.FalhaRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RelatorioServiceImpl implements RelatorioService{
    private FalhaRepository falhaRep = new FalhaRepository();
    private EquipamentoRepository eqpRep = new EquipamentoRepository();
    private AcaoCorretivaRepository acaoRep = new AcaoCorretivaRepository();

    @Override
    public List<RelatorioParadaDTO> gerarRelatorioTempoParada() throws SQLException {
        return falhaRep.buscarTodosRelatoriosParada();
    }

    @Override
    public List<Equipamento> buscarEquipamentosSemFalhasPorPeriodo(LocalDate dataInicio, LocalDate datafim) throws SQLException {
        return eqpRep.buscarEquipamentosSemFalha(datafim, datafim);
    }

    @Override
    public Optional<FalhaDetalhadaDTO> buscarDetalhesCompletosFalha(long falhaId) throws SQLException {
        Falha falha = falhaRep.buscarFalhaId(falhaId);
        if(falha == null){
            throw new RuntimeException();
        }
        Equipamento equipamento = eqpRep.buscarEquipamentoPorId(falha.getEquipamentoId());
        if (equipamento == null) {
            throw new RuntimeException();
        }
        List<String> acoes = acaoRep.buscarAcaoCorretivaPorIdFalha(falhaId);
        return Optional.of(new FalhaDetalhadaDTO(falha, equipamento, acoes));
    }

    @Override
    public List<EquipamentoContagemFalhasDTO> gerarRelatorioManutencaoPreventiva(int contagemMinimaFalhas) throws SQLException{
        if(contagemMinimaFalhas < 1){
            throw new RuntimeException("Valor informado inválido");
        }
        return eqpRep.gerarRelatorioManutencaoPreventiva(contagemMinimaFalhas);
    }
}
