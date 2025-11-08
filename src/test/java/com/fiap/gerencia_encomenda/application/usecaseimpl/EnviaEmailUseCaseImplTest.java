package com.fiap.gerencia_encomenda.application.usecaseimpl;

import com.fiap.gerencia_encomenda.application.gateway.EmailGateway;
import com.fiap.gerencia_encomenda.application.models.EnviaEmailCommand;
import com.fiap.gerencia_encomenda.domain.notificacao.Notificacao;
import com.resend.core.exception.ResendException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EnviaEmailUseCaseImplTest {

    @Mock
    private EmailGateway emailGateway;

    @InjectMocks
    private EnviaEmailUseCaseImpl enviaEmailUseCase;

    @Test
    void executeDeveEnviarEmailComSucesso() throws ResendException {
        // Arrange
        EnviaEmailCommand command = EnviaEmailCommand.of(
                "Teste",
                "manolonit3@gmail.com",
                "teste"
        );

        Notificacao notificacaoEsperada = Notificacao.instanceOf(
                command.titulo(),
                command.destinatario(),
                command.destinatario()
        );

        enviaEmailUseCase.execute(command);

        Mockito.verify(emailGateway, Mockito.times(1)).enviarEmail(Mockito.refEq(notificacaoEsperada));
    }
}
