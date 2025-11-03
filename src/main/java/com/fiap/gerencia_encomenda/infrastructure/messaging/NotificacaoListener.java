package com.fiap.gerencia_encomenda.infrastructure.messaging;

import com.fiap.gerencia_encomenda.application.models.EnviaEmailCommand;
import com.fiap.gerencia_encomenda.domain.events.NotificacaoEvent;
import com.fiap.gerencia_encomenda.usecase.EnviaEmailUseCase;
import com.resend.core.exception.ResendException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class NotificacaoListener {

    private final EnviaEmailUseCase enviaEmailUseCase;

    public NotificacaoListener(EnviaEmailUseCase enviaEmailUseCase) {
        this.enviaEmailUseCase = enviaEmailUseCase;
    }

    private static final Logger logger = LoggerFactory.getLogger(NotificacaoListener.class);

    @Bean
    public Consumer<NotificacaoEvent> notificacaoInput() {
        return notificacao -> {
            try {
                logger.info("Nova notificação recebida - Título: {}, Destinatário: {}",
                        notificacao.titulo(), notificacao.destinatario());
                processarNotificacao(notificacao);

                logger.info("Notificação processada com sucesso para: {}",
                        notificacao.destinatario());

            } catch (Exception e) {
                logger.error("Erro ao processar notificação: {}", e.getMessage(), e);
                throw new RuntimeException("Falha no processamento da notificação", e);
            }
        };
    }

    private void processarNotificacao(NotificacaoEvent notificacao) throws ResendException {
        EnviaEmailCommand command = EnviaEmailCommand.of(
                notificacao.titulo(),
                notificacao.destinatario(),
                notificacao.mensagem()
        );
        enviaEmailUseCase.execute(command);
        logger.debug("Processando notificação: {}", notificacao);

    }
}
