package org.example.service;

import org.example.model.AcaoCorretiva;
import org.example.repository.AcaoCorretivaRepository;
import org.example.repository.EquipamentoRepository;
import org.example.repository.FalhaRepository;

import java.sql.SQLException;

public class AcaoCorretivaServiceImpl implements AcaoCorretivaService{
    @Override
    public AcaoCorretiva registrarConclusaoDeAcao(AcaoCorretiva acao) throws SQLException {
        var rep = new AcaoCorretivaRepository();
        var repFalha = new FalhaRepository();
        var repEqp = new EquipamentoRepository();

        if(!repFalha.verificarIdAberto(acao.getFalhaId())){
            throw new RuntimeException();
        }
        repFalha.atualizarStatus(acao.getFalhaId());

        if(repFalha.getStatus(acao.getFalhaId()).equals("CRITICA")){
            repEqp.atualizarStatus(repFalha.getEqpId(acao.getFalhaId()), "OPERACIONAL");
        }
        return rep.registrarConclusaoDeAcao(acao);
    }
}
