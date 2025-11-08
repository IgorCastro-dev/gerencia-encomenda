package com.fiap.gerencia_encomenda.application.usecaseimpl;

import com.fiap.gerencia_encomenda.application.gateway.MoradorGateway;
import com.fiap.gerencia_encomenda.application.models.CadastraMoradorCommand;
import com.fiap.gerencia_encomenda.domain.exception.MoradorComDadosExistenteException;
import com.fiap.gerencia_encomenda.domain.morador.Morador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CadastraMoradorUseCaseImplTest {

    @Mock
    private MoradorGateway moradorGateway;

    @InjectMocks
    private CadastraMoradorUseCaseImpl moradorUseCaseImpl;

    @Test
    void executeDeveLancarExcecaoQuandoMoradorJaExiste() {
        CadastraMoradorCommand command = CadastraMoradorCommand.of(
                "teste", "123456789", "21233432424", "teste@gmail.com", 123
        );
        Morador moradorExistente = Morador.instanceOf(UUID.randomUUID(),
                "teste", "123456789", "21233432424", "teste@gmail.com", 123);

        Mockito.when(moradorGateway.buscaPorTelefoneOuEmailOuCpf(
                        "21233432424", "teste@gmail.com", "123456789"))
                .thenReturn(Optional.of(moradorExistente));

        Assertions.assertThrows(MoradorComDadosExistenteException.class, () -> {
            moradorUseCaseImpl.execute(command);
        });

        Mockito.verify(moradorGateway, Mockito.never()).salvar(Mockito.any());
    }

    @Test
    void executeDeveSalvarMoradorQuandoNaoExisteDuplicidade() {
        CadastraMoradorCommand command = CadastraMoradorCommand.of(
                "teste", "123456789", "21233432424", "teste@gmail.com", 123
        );

        Mockito.when(moradorGateway.buscaPorTelefoneOuEmailOuCpf(
                        "21233432424", "teste@gmail.com", "123456789"))
                .thenReturn(Optional.empty());

        Mockito.when(moradorGateway.salvar(Mockito.any(Morador.class)))
                .thenReturn("morador salvo");

        String resultado = moradorUseCaseImpl.execute(command);

        Assertions.assertEquals("morador salvo", resultado);
        Mockito.verify(moradorGateway, Mockito.times(1)).salvar(Mockito.any(Morador.class));
    }

}