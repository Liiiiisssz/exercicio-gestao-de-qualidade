package org.example.service;

import org.example.model.Falha;
import org.example.repository.EquipamentoRepository;
import org.example.repository.FalhaRepository;

import java.sql.SQLException;
import java.util.List;

public class FalhaServiceImpl implements FalhaService{
    private FalhaRepository rep = new FalhaRepository();
    private EquipamentoRepository repEquip = new EquipamentoRepository();

    @Override
    public Falha registrarNovaFalha(Falha falha) throws SQLException {
        if(repEquip.buscarEquipamentoPorId(falha.getEquipamentoId()) == null){
            throw new RuntimeException();
        }
        falha.setStatus("ABERTA");

        if(falha.getCriticidade().equals("CRITICO")){
            repEquip.atualizarStatus(falha.getEquipamentoId(), "EM_MANUTENCAO");
        }
        return rep.registrarNovaFalha(falha);
    }

    @Override
    public List<Falha> buscarFalhasCriticasAbertas() throws SQLException {
        return rep.buscarFalhasCriticasAbertas();
    }
}
