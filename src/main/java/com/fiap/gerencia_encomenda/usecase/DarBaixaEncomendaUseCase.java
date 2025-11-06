package com.fiap.gerencia_encomenda.usecase;

import com.fiap.gerencia_encomenda.application.models.BaixaEncomendaCommand;
import com.fiap.gerencia_encomenda.domain.encomenda.Encomenda;

public interface DarBaixaEncomendaUseCase {
    String execute(BaixaEncomendaCommand command);
}
