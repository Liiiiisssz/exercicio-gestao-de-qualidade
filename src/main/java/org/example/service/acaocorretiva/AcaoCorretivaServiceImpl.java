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

        Falha falha = falhaRep.buscarFalhaId(acao.getFalhaId());
        if(falha == null){
            throw new RuntimeException("Falha não encontrada!");
        }
        if(falha.getCriticidade().equals("CRITICA")){
            eqpRep.atualizarStatus(falha.getEquipamentoId(), "OPERACIONAL");
        }
        falhaRep.atualizarStatus(acao.getFalhaId());
        return rep.registrarConclusaoDeAcao(acao);
    }
}
