package com.fiap.gerencia_encomenda.infrastructure.config;

import com.fiap.gerencia_encomenda.application.gateway.EncomendaGateway;
import com.fiap.gerencia_encomenda.application.gateway.MoradorGateway;
import com.fiap.gerencia_encomenda.application.gateway.NotificacaoGateway;
import com.fiap.gerencia_encomenda.application.usecaseimpl.CadastraMoradorUseCaseImpl;
import com.fiap.gerencia_encomenda.application.usecaseimpl.RegistraEncomendaUseCaseImpl;
import com.fiap.gerencia_encomenda.usecase.CadastraMoradorUseCase;
import com.fiap.gerencia_encomenda.usecase.RegistraEncomendaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncomendaUseCaseConfig {

    @Bean
    public RegistraEncomendaUseCase produceRegistraEncomendaUseCase(
            final MoradorGateway moradorGateway,
            final NotificacaoGateway notificacaoGateway,
            final EncomendaGateway encomendaGateway
            ) {
        return new RegistraEncomendaUseCaseImpl(moradorGateway,notificacaoGateway,encomendaGateway);
    }
}
