package com.fiap.gerencia_encomenda.application.usecaseimpl;

import com.fiap.gerencia_encomenda.application.gateway.EncomendaGateway;
import com.fiap.gerencia_encomenda.application.gateway.MoradorGateway;
import com.fiap.gerencia_encomenda.application.gateway.NotificacaoGateway;
import com.fiap.gerencia_encomenda.application.models.RegistraEncomendaCommand;
import com.fiap.gerencia_encomenda.domain.encomenda.Encomenda;
import com.fiap.gerencia_encomenda.domain.exception.MoradorComNomeEApartamentoException;
import com.fiap.gerencia_encomenda.domain.notificacao.Notificacao;
import com.fiap.gerencia_encomenda.usecase.RegistraEncomendaUseCase;
import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.UUID;

public class RegistraEncomendaUseCaseImpl implements RegistraEncomendaUseCase {
    private final MoradorGateway moradorGateway;
    private final NotificacaoGateway notificacaoGateway;
    private final EncomendaGateway encomendaGateway;

    public RegistraEncomendaUseCaseImpl(
            MoradorGateway moradorGateway,
            NotificacaoGateway notificacaoGateway,
            EncomendaGateway encomendaGateway
    ) {
        this.moradorGateway = moradorGateway;
        this.notificacaoGateway = notificacaoGateway;
        this.encomendaGateway = encomendaGateway;
    }

    @Override
    @Transactional
    public String execute(RegistraEncomendaCommand command) {
        String email = verificarMorador(command);
        Encomenda encomenda = criarEncomenda(command);
        encomendaGateway.salvarEncomenda(encomenda);
        Notificacao notificacao = Notificacao.instanceOf(
                "Encomenda Registrada",
                email,
                "Sua encomenda chegou: " + command.descricao()
        );
        notificacaoGateway.enviarNotificacao(notificacao);

        return "Encomenda registrada e notificação enviada com sucesso";
    }

    private Encomenda criarEncomenda(RegistraEncomendaCommand command) {
        return Encomenda.instanceOf(
                UUID.randomUUID(),
                command.nomeMorador(),
                command.apartamento(),
                command.descricao()
        );
    }

    private String verificarMorador(RegistraEncomendaCommand command) {
        Optional<String> emailOptional = moradorGateway.buscaEmailPorNomeEApartamento(
                command.nomeMorador(),
                command.apartamento()
        );

        if (emailOptional.isEmpty()) {
            throw new MoradorComNomeEApartamentoException(
                    command.nomeMorador(),
                    command.apartamento()
            );
        }
        return emailOptional.get();
    }
}
