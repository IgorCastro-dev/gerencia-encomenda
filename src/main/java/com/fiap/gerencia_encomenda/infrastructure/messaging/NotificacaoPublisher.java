package com.fiap.gerencia_encomenda.infrastructure.messaging;
import com.fiap.gerencia_encomenda.domain.notificacao.Notificacao;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class NotificacaoPublisher {

    private static final Logger logger = LoggerFactory.getLogger(NotificacaoPublisher.class);
    private final StreamBridge streamBridge;
    private static final String BINDING_NAME = "notificacaoOutput-out-0";

    public NotificacaoPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public boolean enviarNotificacao(Notificacao notificacao) {
        try {
            logger.info("Enviando notificação para o Kafka: {}", notificacao.getTitulo());

            boolean enviado = streamBridge.send(BINDING_NAME, notificacao);

            if (enviado) {
                logger.info("Notificação enviada com sucesso para o destinatário: {}",
                        notificacao.getDestinatario());
            } else {
                logger.error("Falha ao enviar notificação para: {}",
                        notificacao.getDestinatario());
            }

            return enviado;

        } catch (Exception e) {
            logger.error("Erro ao enviar notificação para o Kafka: {}", e.getMessage(), e);
            return false;
        }
    }

    public boolean enviarNotificacao(String titulo, String destinatario, String mensagem) {
        Notificacao notificacao = Notificacao.instanceOf(titulo, destinatario, mensagem);
        return enviarNotificacao(notificacao);
    }
}