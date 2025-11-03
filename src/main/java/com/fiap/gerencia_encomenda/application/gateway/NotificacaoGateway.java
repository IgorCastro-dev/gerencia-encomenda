package com.fiap.gerencia_encomenda.application.gateway;

import com.fiap.gerencia_encomenda.domain.notificacao.Notificacao;

public interface NotificacaoGateway {
    void enviarNotificacao(Notificacao notificacao);
}
