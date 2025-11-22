package com.fiap.gerencia_encomenda.infrastructure.gatewayimpl;

import com.fiap.gerencia_encomenda.domain.notificacao.Notificacao;
import com.fiap.gerencia_encomenda.infrastructure.smtp.EmailClient;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailGatewayImplTest {

    @InjectMocks
    private EmailGatewayImpl emailGateway;

    @Mock
    private EmailClient emailClient;

    @Mock
    private CreateEmailResponse createEmailResponse;

    @BeforeEach
    void setUp() {
        emailGateway = new EmailGatewayImpl("from@test.com", emailClient);
    }

    @Test
    void deveEnviarEmailComSucesso() throws ResendException {
        Notificacao notificacao = new Notificacao(
                "Teste Subject",
                "manolonit3@gmail.com",
                "Mensagem de teste"
        );

        when(emailClient.enviarEmail(
                eq("from@test.com"),
                eq("manolonit3@gmail.com"),
                eq("Teste Subject"),
                contains("Mensagem de teste")
        )).thenReturn(createEmailResponse);

        when(createEmailResponse.getId()).thenReturn("email-123");

        // Act
        emailGateway.enviarEmail(notificacao);

        // Assert
        verify(emailClient).enviarEmail(
                "from@test.com",
                "manolonit3@gmail.com",
                "Teste Subject",
                "<strong>Mensagem de teste</strong>"
        );
    }

    @Test
    void deveLancarExcecaoQuandoClientFalhar() throws ResendException {
        Notificacao notificacao = new Notificacao(
                "Teste Subject",
                "manolonit3@gmail.com",
                "Mensagem de teste"
        );

        when(emailClient.enviarEmail(any(), any(), any(), any()))
                .thenThrow(new ResendException("Erro de API"));

        assertThrows(ResendException.class, () -> emailGateway.enviarEmail(notificacao));
    }

    @Test
    void deveLogarInformacoesCorretasNoSucesso() throws ResendException {
        Notificacao notificacao = new Notificacao(
                "Teste Subject",
                "manolonit3@gmail.com",
                "Mensagem de teste"
        );

        when(emailClient.enviarEmail(any(), any(), any(), any()))
                .thenReturn(createEmailResponse);
        when(createEmailResponse.getId()).thenReturn("email-123");

        emailGateway.enviarEmail(notificacao);

        verify(emailClient).enviarEmail(
                "from@test.com",
                "manolonit3@gmail.com",
                "Teste Subject",
                "<strong>Mensagem de teste</strong>"
        );
    }
}