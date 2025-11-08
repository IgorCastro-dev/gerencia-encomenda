package com.fiap.gerencia_encomenda.infrastructure.smtp;

import com.fiap.gerencia_encomenda.application.gateway.EmailGateway;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ResendClient implements EmailClient{

    private final Resend resend;

    public ResendClient(@Value("${resend.api-key}") String apiKey) {
        this.resend = new Resend(apiKey);
    }

    public CreateEmailResponse enviarEmail(String from, String to, String subject, String html) throws ResendException {
        CreateEmailOptions params = CreateEmailOptions.builder()
                .from(from)
                .to(to)
                .subject(subject)
                .html(html)
                .build();

        return resend.emails().send(params);
    }
}