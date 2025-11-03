package com.fiap.gerencia_encomenda.usecase;

import com.fiap.gerencia_encomenda.application.models.RegistraEncomendaCommand;

public interface RegistraEncomendaUseCase {
    String execute(RegistraEncomendaCommand command);
}
