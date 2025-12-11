package org.example.service.acaocorretiva;

import org.example.model.AcaoCorretiva;
import org.example.model.Falha;
import org.example.repository.AcaoCorretivaRepository;
import org.example.repository.EquipamentoRepository;
import org.example.repository.FalhaRepository;

import java.sql.SQLException;

public class AcaoCorretivaServiceImpl implements AcaoCorretivaService{

    @Override
    public AcaoCorretiva registrarConclusaoDeAcao(AcaoCorretiva acao) throws SQLException {
        var falhaRep = new FalhaRepository();
        var eqpRep = new EquipamentoRepository();
        var rep = new AcaoCorretivaRepository();
        Falha falha = falhaRep.buscarFalhaPorId(acao.getFalhaId());
        if(falha == null){
            throw new RuntimeException("Falha não encontrada!");
        }
        rep.registrarConclusaoDeAcao(acao);
        falhaRep.atualizarStatus(acao.getFalhaId());
        if(falha.getCriticidade().equals("CRITICA")){
            eqpRep.atualizarStatus(falha.getEquipamentoId(), "OPERACIONAL");
        }
        return acao;
    }
}
