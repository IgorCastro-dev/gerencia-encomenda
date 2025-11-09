package com.fiap.gerencia_encomenda.infrastructure.gatewayimpl;

import com.fiap.gerencia_encomenda.domain.encomenda.Encomenda;
import com.fiap.gerencia_encomenda.domain.encomenda.StatusEncomenda;
import com.fiap.gerencia_encomenda.infrastructure.persistence.encomenda.EncomendaJpaEntity;
import com.fiap.gerencia_encomenda.infrastructure.persistence.encomenda.EncomendaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class EncomendaGatewayImplTest {

    @Mock
    private EncomendaRepository encomendaRepository;

    @InjectMocks
    private EncomendaGatewayImpl encomendaGateway;

    @Test
    void deveSalvarEncomenda() {
        // Arrange
        UUID id = UUID.randomUUID();
        Encomenda encomenda = Encomenda.instanceOf(
                id,
                "Joao Silva",
                101,
                "Pacote da Amazon"
        );

        System.out.println("=== DEBUG INICIO ===");
        System.out.println("Encomenda criada: " + encomenda);
        System.out.println("Encomenda ID: " + encomenda.getId());
        System.out.println("Encomenda Status: " + encomenda.getStatus());

        // Crie uma entidade manualmente para o mock
        EncomendaJpaEntity entity = new EncomendaJpaEntity();
        entity.setId(encomenda.getId());
        entity.setNomeMorador(encomenda.getNomeMorador());
        entity.setApartamento(encomenda.getApartamento());
        entity.setDescricao(encomenda.getDescricao());
        entity.setDataRecebimento(encomenda.getDataRecebimento());
        entity.setDataEntregue(encomenda.getDataEntregue());
        entity.setStatus(encomenda.getStatus());

        System.out.println("Entity criada manualmente: " + entity);
        System.out.println("Entity ID: " + entity.getId());
        System.out.println("Entity Status: " + entity.getStatus());

        // Mock do repositório
        when(encomendaRepository.save(any(EncomendaJpaEntity.class))).thenReturn(entity);

        // Act
        System.out.println("=== CHAMANDO salvarEncomenda ===");
        encomendaGateway.salvarEncomenda(encomenda);
        System.out.println("=== salvarEncomenda CONCLUÍDO ===");

        // Assert
        verify(encomendaRepository, times(1)).save(any(EncomendaJpaEntity.class));
    }

    @Test
    void deveBuscarEncomendaPorId() {
        UUID id = UUID.randomUUID();
        EncomendaJpaEntity entity = new EncomendaJpaEntity();
        when(encomendaRepository.getReferenceById(id)).thenReturn(entity);

        EncomendaJpaEntity resultado = encomendaGateway.buscarEncomendaPorId(id);

        assertEquals(entity, resultado);
        verify(encomendaRepository).getReferenceById(id);
    }
}