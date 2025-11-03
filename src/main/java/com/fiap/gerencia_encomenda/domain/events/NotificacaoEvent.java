package com.fiap.gerencia_encomenda.domain.events;

public record NotificacaoEvent(
        String titulo,
        String destinatario,
        String mensagem
) {
}
