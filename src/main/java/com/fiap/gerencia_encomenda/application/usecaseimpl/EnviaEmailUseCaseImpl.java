package com.fiap.gerencia_encomenda.application.usecaseimpl;

import com.fiap.gerencia_encomenda.application.gateway.EmailGateway;
import com.fiap.gerencia_encomenda.application.models.EnviaEmailCommand;
import com.fiap.gerencia_encomenda.domain.notificacao.Notificacao;
import com.fiap.gerencia_encomenda.usecase.EnviaEmailUseCase;
import com.resend.core.exception.ResendException;

public class EnviaEmailUseCaseImpl implements EnviaEmailUseCase {

    private final EmailGateway emailGateway;

    public EnviaEmailUseCaseImpl(EmailGateway emailGateway) {
        this.emailGateway = emailGateway;
    }

    @Override
    public void execute(EnviaEmailCommand command) throws ResendException {
        Notificacao notificacao = Notificacao.instanceOf(
                command.titulo(),
                command.destinatario(),
                command.destinatario()
        );
        emailGateway.enviarEmail(notificacao);
    }
}
