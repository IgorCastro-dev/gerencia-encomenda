package com.fiap.gerencia_encomenda.application.usecaseimpl;

import com.fiap.gerencia_encomenda.application.gateway.EncomendaGateway;
import com.fiap.gerencia_encomenda.application.models.BaixaEncomendaCommand;
import com.fiap.gerencia_encomenda.domain.encomenda.Encomenda;
import com.fiap.gerencia_encomenda.domain.encomenda.StatusEncomenda;
import com.fiap.gerencia_encomenda.infrastructure.persistence.encomenda.EncomendaJpaEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;


@ExtendWith(MockitoExtension.class)
class DarBaixaEncomendaUseCaseImplTest {

    @Mock
    private EncomendaGateway encomendaGateway;

    @InjectMocks
    private DarBaixaEncomendaUseCaseImpl darBaixaEncomendaUseCase;

    @Test
    void executeDeveDarBaixaNaEncomendaComSucesso() {
        UUID idEncomenda = UUID.randomUUID();
        BaixaEncomendaCommand command = new BaixaEncomendaCommand(idEncomenda);

        EncomendaJpaEntity encomendaJpaEntity = new EncomendaJpaEntity();
        Encomenda encomenda = Encomenda.instanceOf(idEncomenda,
                "teste",
                123,
                "teste");
        encomenda.setStatus(StatusEncomenda.RECEBIDA);

        Mockito.mockStatic(EncomendaJpaEntity.class).when(() ->
                EncomendaJpaEntity.toDomain(encomendaJpaEntity)).thenReturn(encomenda);

        Mockito.when(encomendaGateway.buscarEncomendaPorId(idEncomenda)).thenReturn(encomendaJpaEntity);

        String resultado = darBaixaEncomendaUseCase.execute(command);

        Assertions.assertEquals("Baixa na encomenda realizada com sucesso!", resultado);
        Assertions.assertEquals(StatusEncomenda.ENTREGUE, encomenda.getStatus());
        Assertions.assertNotNull(encomenda.getDataRecebimento());

        Mockito.verify(encomendaGateway).buscarEncomendaPorId(idEncomenda);
        Mockito.verify(encomendaGateway).salvarEncomenda(encomenda);
    }
}
