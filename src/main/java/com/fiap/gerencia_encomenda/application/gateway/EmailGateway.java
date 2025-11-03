package com.fiap.gerencia_encomenda.application.gateway;

import com.fiap.gerencia_encomenda.domain.notificacao.Notificacao;
import com.resend.core.exception.ResendException;

public interface EmailGateway {
    void enviarEmail(Notificacao notificacao) throws ResendException;
}
