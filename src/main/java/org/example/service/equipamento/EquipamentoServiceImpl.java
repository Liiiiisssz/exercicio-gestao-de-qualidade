package org.example.service.equipamento;

import org.example.model.Equipamento;
import org.example.repository.EquipamentoRepository;

import java.sql.SQLException;

public class EquipamentoServiceImpl implements EquipamentoService{
    EquipamentoRepository rep = new EquipamentoRepository();

    @Override
    public Equipamento criarEquipamento(Equipamento equipamento) throws SQLException {
        equipamento.setStatusOperacional("OPERACIONAL");
        return rep.criarEquipamento(equipamento);
    }

    @Override
    public Equipamento buscarEquipamentoPorId(Long id) throws SQLException {
        Equipamento eqp = rep.buscarEquipamentoPorId(id);
        if(eqp == null){
            throw new RuntimeException("Equipamento não encontrado!");
        }
        return eqp;
    }
}
