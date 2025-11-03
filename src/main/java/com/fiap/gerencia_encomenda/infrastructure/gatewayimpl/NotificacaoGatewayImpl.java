package com.fiap.gerencia_encomenda.infrastructure.gatewayimpl;

import com.fiap.gerencia_encomenda.application.gateway.NotificacaoGateway;
import com.fiap.gerencia_encomenda.domain.notificacao.Notificacao;
import com.fiap.gerencia_encomenda.infrastructure.messaging.NotificacaoPublisher;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoGatewayImpl implements NotificacaoGateway {

    private final NotificacaoPublisher notificacaoPublisher;

    public NotificacaoGatewayImpl(NotificacaoPublisher notificacaoPublisher) {
        this.notificacaoPublisher = notificacaoPublisher;
    }

    @Override
    public void enviarNotificacao(Notificacao notificacao) {
        boolean sucesso = notificacaoPublisher.enviarNotificacao(notificacao);

        if (!sucesso) {
            throw new RuntimeException("Falha ao enviar notificação para o Kafka");
        }
    }
}
