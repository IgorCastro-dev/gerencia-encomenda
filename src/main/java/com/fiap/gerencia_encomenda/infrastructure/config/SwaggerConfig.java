package com.fiap.gerencia_encomenda.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Encomendas API")
                        .description("API para gerenciamento de encomendas de condomínio")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Igor E J Castro")
                                .email("manolonit3@gmail.com")));
    }
}
