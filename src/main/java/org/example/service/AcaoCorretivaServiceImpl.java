package org.example.service;

import org.example.model.AcaoCorretiva;
import org.example.model.Falha;
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

        Falha falha = repFalha.buscarFalhaId(acao.getFalhaId());
        if(falha == null){
            throw new RuntimeException("Falha não encontrada!");
        }
        repFalha.atualizarStatus(falha.getId());

        if(falha.getCriticidade().equals("CRITICA")){
            repEqp.atualizarStatus(falha.getEquipamentoId(), "OPERACIONAL");
        }
        return rep.registrarConclusaoDeAcao(acao);
    }
}
