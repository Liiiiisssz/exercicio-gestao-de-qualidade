package org.example.service;

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
    public Equipamento buscarEquipamentoPorId(long id) throws SQLException {
        return rep.buscarEquipamentoPorId(id);
    }
}
