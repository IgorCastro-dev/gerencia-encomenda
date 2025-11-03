package com.fiap.gerencia_encomenda.infrastructure.config;

import com.fiap.gerencia_encomenda.application.gateway.EmailGateway;
import com.fiap.gerencia_encomenda.application.usecaseimpl.EnviaEmailUseCaseImpl;
import com.fiap.gerencia_encomenda.usecase.EnviaEmailUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailUseCaseConfig {

    @Bean
    public EnviaEmailUseCase produceEnviaEmailUseCase(
            final EmailGateway emailGateway
            ) {
        return new EnviaEmailUseCaseImpl(emailGateway);
    }
}
