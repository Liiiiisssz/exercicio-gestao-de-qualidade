package org.example.service.equipamento;

import org.example.model.Equipamento;
import org.example.repository.EquipamentoRepository;

import java.sql.SQLException;

public class EquipamentoServiceImpl implements EquipamentoService{
    private EquipamentoRepository rep = new EquipamentoRepository();

    @Override
    public Equipamento criarEquipamento(Equipamento equipamento) throws SQLException {
        return rep.criarEquipamento(equipamento);
    }

    @Override
    public Equipamento buscarEquipamentoPorId(Long id) throws SQLException {
        if(rep.buscarEquipamentoPorId(id) == null){
            throw new RuntimeException("Equipamento não encontrado!");
        }
        return rep.buscarEquipamentoPorId(id);
    }
}
