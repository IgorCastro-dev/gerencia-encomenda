package com.fiap.gerencia_encomenda.infrastructure.gatewayimpl;

import com.fiap.gerencia_encomenda.domain.encomenda.Encomenda;
import com.fiap.gerencia_encomenda.infrastructure.persistence.encomenda.EncomendaJpaEntity;
import com.fiap.gerencia_encomenda.infrastructure.persistence.encomenda.EncomendaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class EncomendaGatewayImplTest {

    @Mock
    private EncomendaRepository encomendaRepository;

    @InjectMocks
    private EncomendaGatewayImpl encomendaGateway;

    @Test
    void salvarEncomendaDevePersistirEntidade() {
        Encomenda encomenda = Encomenda.instanceOf(
                UUID.randomUUID(), "João", 101, "Caixa com livros"
        );
        EncomendaJpaEntity entity = EncomendaJpaEntity.fromDomain(encomenda);

        encomendaGateway.salvarEncomenda(encomenda);

        Mockito.verify(encomendaRepository).save(Mockito.argThat(e ->
                e.getNomeMorador().equals("João") &&
                        e.getApartamento().equals(101) &&
                        e.getDescricao().equals("Caixa com livros")
        ));
    }

    @Test
    void buscarEncomendaPorIdDeveRetornarEntidade() {
        UUID id = UUID.randomUUID();
        EncomendaJpaEntity entity = new EncomendaJpaEntity();
        Mockito.when(encomendaRepository.getReferenceById(id)).thenReturn(entity);

        EncomendaJpaEntity resultado = encomendaGateway.buscarEncomendaPorId(id);

        Assertions.assertNotNull(resultado);
        Mockito.verify(encomendaRepository).getReferenceById(id);
    }
}
