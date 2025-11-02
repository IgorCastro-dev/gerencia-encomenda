package com.fiap.gerencia_encomenda.infrastructure.config;

import com.fiap.gerencia_encomenda.application.gateway.MoradorGateway;
import com.fiap.gerencia_encomenda.application.usecaseimpl.CadastraMoradorUseCaseImpl;
import com.fiap.gerencia_encomenda.application.usecaseimpl.ListaMoradoresUseCaseImpl;
import com.fiap.gerencia_encomenda.usecase.CadastraMoradorUseCase;
import com.fiap.gerencia_encomenda.usecase.ListaMoradoresUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MoradorUseCaseConfig {

    @Bean
    public CadastraMoradorUseCase produceCadastraMoradorUseCase(
            final MoradorGateway moradorGateway
    ) {
        return new CadastraMoradorUseCaseImpl(moradorGateway);
    }

    @Bean
    public ListaMoradoresUseCase produceListaMoradoresUseCase(
            final MoradorGateway moradorGateway
    ) {
        return new ListaMoradoresUseCaseImpl(moradorGateway);
    }
}
