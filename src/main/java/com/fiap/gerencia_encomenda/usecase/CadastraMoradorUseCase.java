package com.fiap.gerencia_encomenda.usecase;

import com.fiap.gerencia_encomenda.application.models.CadastraMoradorCommand;

public interface CadastraMoradorUseCase {
    String execute(CadastraMoradorCommand command);
}
