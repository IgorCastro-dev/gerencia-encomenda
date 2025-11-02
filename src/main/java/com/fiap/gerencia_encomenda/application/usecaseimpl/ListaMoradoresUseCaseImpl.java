package com.fiap.gerencia_encomenda.application.usecaseimpl;

import com.fiap.gerencia_encomenda.application.gateway.MoradorGateway;
import com.fiap.gerencia_encomenda.domain.morador.Morador;
import com.fiap.gerencia_encomenda.usecase.ListaMoradoresUseCase;

import java.util.List;

public class ListaMoradoresUseCaseImpl implements ListaMoradoresUseCase {

    private final MoradorGateway moradorGateway;

    public ListaMoradoresUseCaseImpl(MoradorGateway moradorGateway) {
        this.moradorGateway = moradorGateway;
    }

    @Override
    public List<Morador> execute() {
        return moradorGateway.listarMoradores();
    }
}
