package com.fiap.gerencia_encomenda.usecase;

import com.fiap.gerencia_encomenda.application.models.CadastraMoradorCommand;
import com.fiap.gerencia_encomenda.domain.morador.Morador;

public interface CadastraMoradorUseCase {
    String execute(CadastraMoradorCommand command);
}
