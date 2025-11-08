package com.fiap.gerencia_encomenda.infrastructure.smtp;

import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailResponse;

public interface EmailClient {
    CreateEmailResponse enviarEmail(String from, String to, String subject, String html) throws ResendException;
}