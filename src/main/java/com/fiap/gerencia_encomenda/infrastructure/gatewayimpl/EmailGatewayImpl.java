package com.fiap.gerencia_encomenda.infrastructure.gatewayimpl;

import com.fiap.gerencia_encomenda.application.gateway.EmailGateway;
import com.fiap.gerencia_encomenda.domain.notificacao.Notificacao;
import com.fiap.gerencia_encomenda.infrastructure.smtp.EmailClient;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class EmailGatewayImpl implements EmailGateway {

    private static final Logger logger = LoggerFactory.getLogger(EmailGatewayImpl.class);

    private final String fromEmail;
    private final EmailClient emailClient;

    public EmailGatewayImpl(
            @Value("${resend.from-email}") String fromEmail,
            EmailClient emailClient) {
        this.fromEmail = fromEmail;
        this.emailClient = emailClient;
    }

    @Override
    public void enviarEmail(Notificacao notificacao) throws ResendException {
        try {
            logger.info("Enviando email para: {}", notificacao.getDestinatario());

            CreateEmailResponse data = emailClient.enviarEmail(
                    fromEmail,
                    notificacao.getDestinatario(),
                    notificacao.getTitulo(),
                    "<strong>" + notificacao.getMensagem() + "</strong>"
            );

            logger.info("Email enviado com sucesso. ID: {}, Para: {}",
                    data.getId(), notificacao.getDestinatario());

        } catch (ResendException e) {
            logger.error("Erro ao enviar email para {}: {}",
                    notificacao.getDestinatario(), e.getMessage(), e);
            throw new ResendException("Falha ao enviar email", e);
        }
    }
}
