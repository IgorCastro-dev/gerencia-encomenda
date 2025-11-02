package com.fiap.gerencia_encomenda.usecase;

import com.fiap.gerencia_encomenda.domain.notificacao.Notificacao;

public interface NotificaMoradorUseCase {
    String execute(Notificacao notificacao);
}
