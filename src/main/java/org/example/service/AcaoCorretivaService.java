package org.example.service;

import org.example.model.AcaoCorretiva;

import java.sql.SQLException;

public interface AcaoCorretivaService {

    AcaoCorretiva registrarConclusaoDeAcao(AcaoCorretiva acao) throws SQLException;
}
