package org.example.service;

import java.sql.SQLException;
import org.example.model.Equipamento;

public interface EquipamentoService {

    Equipamento criarEquipamento(Equipamento equipamento) throws SQLException;

    Equipamento buscarEquipamentoPorId(Long id) throws SQLException;
}
