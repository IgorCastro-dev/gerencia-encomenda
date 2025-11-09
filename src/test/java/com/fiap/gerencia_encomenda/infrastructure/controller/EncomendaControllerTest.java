package com.fiap.gerencia_encomenda.infrastructure.controller;
import com.fiap.gerencia_encomenda.domain.encomenda.StatusEncomenda;
import com.fiap.gerencia_encomenda.domain.morador.Morador;
import com.fiap.gerencia_encomenda.infrastructure.controller.models.EncomendaRequest;
import com.fiap.gerencia_encomenda.infrastructure.persistence.encomenda.EncomendaJpaEntity;
import com.fiap.gerencia_encomenda.infrastructure.persistence.encomenda.EncomendaRepository;
import com.fiap.gerencia_encomenda.infrastructure.persistence.morador.MoradorJpaEntity;
import com.fiap.gerencia_encomenda.infrastructure.persistence.morador.MoradorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
class EncomendaControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EncomendaRepository encomendaRepository;

    @Autowired
    private MoradorRepository moradorRepository;

    private UUID encomendaId;
    private UUID moradorId;

    @BeforeEach
    void setUp() {
        encomendaRepository.deleteAll();
        moradorRepository.deleteAll();

        // Criar morador (está funcionando)
        Morador morador = Morador.instanceOf(
                UUID.randomUUID(),
                "João Silva",
                "123456678",
                "22233234",
                "joao@email.com",
                101);
        MoradorJpaEntity moradorJpaEntity = MoradorJpaEntity.fromDomain(morador);
        MoradorJpaEntity moradorSalvo = moradorRepository.save(moradorJpaEntity);
        moradorId = moradorSalvo.getId();

        // SOLUÇÃO: Criar EncomendaJpaEntity MANUALMENTE sem usar fromDomain
        UUID encomendaUUID = UUID.randomUUID();

        EncomendaJpaEntity encomendaJpaEntity = new EncomendaJpaEntity();
        encomendaJpaEntity.setId(encomendaUUID);
        encomendaJpaEntity.setNomeMorador("João Silva");
        encomendaJpaEntity.setApartamento(101);
        encomendaJpaEntity.setDescricao("Pacote da Amazon");
        encomendaJpaEntity.setDataRecebimento(LocalDateTime.now()); // Defina um valor
        encomendaJpaEntity.setStatus(StatusEncomenda.RECEBIDA); // Status obrigatório

        System.out.println("DEBUG: EncomendaJpaEntity criada manualmente - ID: " + encomendaJpaEntity.getId());
        System.out.println("DEBUG: Status: " + encomendaJpaEntity.getStatus());
        System.out.println("DEBUG: Nome Morador: " + encomendaJpaEntity.getNomeMorador());

        EncomendaJpaEntity encomendaSalva = encomendaRepository.save(encomendaJpaEntity);
        encomendaId = encomendaSalva.getId();
        System.out.println("Encomenda salva com ID: " + encomendaId);
    }

    @Test
    void deveRegistrarEncomendaComSucesso() {
        EncomendaRequest request = new EncomendaRequest(
                "João Silva",
                101,
                "Pacote da Amazon"
        );

        // Act
        ResponseEntity<String> response = restTemplate.postForEntity(
                "/encomenda/registrar",
                request,
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deveDarBaixaEncomendaComSucesso() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "/encomenda/baixa/" + encomendaId,
                String.class
        );

        EncomendaJpaEntity encomendaAtualizada = encomendaRepository.findById(encomendaId).orElse(null);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("Baixa na encomenda realizada com sucesso!");

        if (encomendaAtualizada != null) {
            assertThat(encomendaAtualizada.getStatus()).isEqualTo(StatusEncomenda.ENTREGUE);
            assertThat(encomendaAtualizada.getDataRecebimento()).isNotNull();
        }
    }


    @Test
    void deveRetornarBadRequestParaUUIDInvalido() {
        String uuidInvalido = "uuid-invalido-123";

        ResponseEntity<String> response = restTemplate.getForEntity(
                "/encomenda/baixa/" + uuidInvalido,
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}