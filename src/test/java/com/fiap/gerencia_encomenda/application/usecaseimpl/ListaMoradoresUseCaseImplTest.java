package com.fiap.gerencia_encomenda.application.usecaseimpl;

import com.fiap.gerencia_encomenda.application.gateway.MoradorGateway;
import com.fiap.gerencia_encomenda.domain.morador.Morador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ListaMoradoresUseCaseImplTest {

    @Mock
    private MoradorGateway moradorGateway;

    @InjectMocks
    private ListaMoradoresUseCaseImpl listaMoradoresUseCase;

    @Test
    void executeDeveRetornarListaDeMoradores() {
        List<Morador> moradoresEsperados = List.of(
                Morador.instanceOf(UUID.randomUUID(), "João", "123456789", "21999999999", "joao@email.com", 101),
                Morador.instanceOf(UUID.randomUUID(), "Maria", "987654321", "21988888888", "maria@email.com", 102)
        );

        Mockito.when(moradorGateway.listarMoradores()).thenReturn(moradoresEsperados);

        List<Morador> resultado = listaMoradoresUseCase.execute();

        Assertions.assertEquals(moradoresEsperados.size(), resultado.size());
        Assertions.assertIterableEquals(moradoresEsperados, resultado);
        Mockito.verify(moradorGateway, Mockito.times(1)).listarMoradores();
    }
}
