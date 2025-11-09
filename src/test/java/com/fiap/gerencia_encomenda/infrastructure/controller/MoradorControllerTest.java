package com.fiap.gerencia_encomenda.infrastructure.controller;
import com.fiap.gerencia_encomenda.infrastructure.controller.models.MoradorRequest;
import com.fiap.gerencia_encomenda.infrastructure.persistence.morador.MoradorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
class MoradorControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MoradorRepository moradorRepository;

    @BeforeEach
    void setUp() {
        moradorRepository.deleteAll();
    }

    @Test
    void deveCadastrarMoradorComSucesso() {
        MoradorRequest request = new MoradorRequest(
                "João Silva",
                "123456700",
                "(11) 99999-9999",
                "joao@email.com",
                101
        );

        ResponseEntity<String> response = restTemplate.postForEntity(
                "/morador/cadastrar",
                request,
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("Morador salvo com sucesso!");
    }

    @Test
    void deveListarMoradores() {
        ResponseEntity<List> response = restTemplate.getForEntity(
                "/morador/listar",
                List.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void deveCadastrarEDepoisListarMorador() {
        MoradorRequest request = new MoradorRequest(
                "Maria Santos",
                "987654300",
                "(11) 88888-8888",
                "maria@email.com",
                102
        );

        ResponseEntity<String> cadastroResponse = restTemplate.postForEntity(
                "/morador/cadastrar",
                request,
                String.class
        );

        assertThat(cadastroResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<List> listaResponse = restTemplate.getForEntity(
                "/morador/listar",
                List.class
        );

        assertThat(listaResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(listaResponse.getBody()).isNotNull();
    }
}