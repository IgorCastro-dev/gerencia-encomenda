package com.fiap.gerencia_encomenda.infrastructure.gatewayimpl;

import com.fiap.gerencia_encomenda.application.gateway.EmailGateway;
import com.fiap.gerencia_encomenda.domain.notificacao.Notificacao;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class EmailGatewayImpl implements EmailGateway {

    private static final Logger logger = LoggerFactory.getLogger(EmailGatewayImpl.class);

    @Value("${resend.api-key}")
    private String apiKey;

    @Value("${resend.from-email}")
    private String fromEmail;

    @Override
    public void enviarEmail(Notificacao notificacao) throws ResendException {
        try {
            logger.info("Enviando email para: {}", notificacao.getDestinatario());

            Resend resend = new Resend(apiKey);

            CreateEmailOptions params = CreateEmailOptions.builder()
                    .from(fromEmail)
                    .to(notificacao.getDestinatario())
                    .subject(notificacao.getTitulo())
                    .html("<strong>" + notificacao.getMensagem() + "</strong>")
                    .build();

            CreateEmailResponse data = resend.emails().send(params);

            logger.info("Email enviado com sucesso. ID: {}, Para: {}",
                    data.getId(), notificacao.getDestinatario());

        } catch (ResendException e) {
            logger.error("Erro ao enviar email para {}: {}",
                    notificacao.getDestinatario(), e.getMessage(), e);
            throw new ResendException("Falha ao enviar email", e);
        }
    }
}
