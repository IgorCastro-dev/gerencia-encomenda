package com.fiap.gerencia_encomenda.infrastructure.gatewayimpl;

import com.fiap.gerencia_encomenda.domain.notificacao.Notificacao;
import com.fiap.gerencia_encomenda.infrastructure.messaging.NotificacaoPublisher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class NotificacaoGatewayImplTest {

    @Mock
    private NotificacaoPublisher notificacaoPublisher;

    @InjectMocks
    private NotificacaoGatewayImpl notificacaoGateway;

    @Test
    void enviarNotificacaoDeveExecutarComSucesso() {
        Notificacao notificacao = Notificacao.instanceOf(
                "Título", "destinatario@email.com", "Mensagem de teste"
        );

        Mockito.when(notificacaoPublisher.enviarNotificacao(notificacao)).thenReturn(true);

        Assertions.assertDoesNotThrow(() -> notificacaoGateway.enviarNotificacao(notificacao));
        Mockito.verify(notificacaoPublisher).enviarNotificacao(notificacao);
    }

    @Test
    void enviarNotificacaoDeveLancarExcecaoQuandoFalha() {
        Notificacao notificacao = Notificacao.instanceOf(
                "Título", "destinatario@email.com", "Mensagem de teste"
        );

        Mockito.when(notificacaoPublisher.enviarNotificacao(notificacao)).thenReturn(false);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            notificacaoGateway.enviarNotificacao(notificacao);
        });

        Assertions.assertEquals("Falha ao enviar notificação para o Kafka", exception.getMessage());
        Mockito.verify(notificacaoPublisher).enviarNotificacao(notificacao);
    }
}
