package com.fiap.gerencia_encomenda.application.usecaseimpl;

import com.fiap.gerencia_encomenda.application.gateway.MoradorGateway;
import com.fiap.gerencia_encomenda.application.models.CadastraMoradorCommand;
import com.fiap.gerencia_encomenda.domain.exception.MoradorComDadosExistenteException;
import com.fiap.gerencia_encomenda.domain.morador.Morador;
import com.fiap.gerencia_encomenda.usecase.CadastraMoradorUseCase;
import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.UUID;

public class CadastraMoradorUseCaseImpl implements CadastraMoradorUseCase {
    private final MoradorGateway moradorGateway;

    public CadastraMoradorUseCaseImpl(MoradorGateway moradorGateway) {
        this.moradorGateway = moradorGateway;
    }

    @Override
    @Transactional
    public String execute(CadastraMoradorCommand command) {
        verificarDuplicidades(command);
        Morador morador = criarMorador(command);
        return moradorGateway.salvar(morador);
    }


    private void verificarDuplicidades(CadastraMoradorCommand command) {
        Optional<Morador> moradorOptional = moradorGateway.buscaPorTelefoneOuEmailOuCpf(command.telefone(),command.email(),command.cpf());
        if (moradorOptional.isPresent()) {
            throw new MoradorComDadosExistenteException(command.telefone(),command.email(),command.cpf());
        }
    }

    private Morador criarMorador(CadastraMoradorCommand command) {
        return Morador.instanceOf(
                UUID.randomUUID(),
                command.nome(),
                command.cpf(),
                command.telefone(),
                command.email(),
                command.apartamento()
        );
    }
}
