package com.fiap.gerencia_encomenda.application.usecaseimpl;

import com.fiap.gerencia_encomenda.application.gateway.EncomendaGateway;
import com.fiap.gerencia_encomenda.application.gateway.MoradorGateway;
import com.fiap.gerencia_encomenda.application.gateway.NotificacaoGateway;
import com.fiap.gerencia_encomenda.application.models.RegistraEncomendaCommand;
import com.fiap.gerencia_encomenda.domain.encomenda.Encomenda;
import com.fiap.gerencia_encomenda.domain.exception.MoradorComNomeEApartamentoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class RegistraEncomendaUseCaseImplTest {

    @Mock
    private MoradorGateway moradorGateway;

    @Mock
    private NotificacaoGateway notificacaoGateway;

    @Mock
    private EncomendaGateway encomendaGateway;

    @InjectMocks
    private RegistraEncomendaUseCaseImpl registraEncomendaUseCase;

    @Test
    void executeDeveRegistrarEncomendaEEnviarNotificacao() {

        RegistraEncomendaCommand command = RegistraEncomendaCommand.of("João", 101, "Caixa com livros");
        String email = "joao@email.com";

        Mockito.when(moradorGateway.buscaEmailPorNomeEApartamento("João", 101))
                .thenReturn(Optional.of(email));

        String resultado = registraEncomendaUseCase.execute(command);

        Assertions.assertEquals("Encomenda registrada e notificação enviada com sucesso", resultado);

        Mockito.verify(moradorGateway).buscaEmailPorNomeEApartamento("João", 101);
        Mockito.verify(encomendaGateway).salvarEncomenda(Mockito.any(Encomenda.class));
        Mockito.verify(notificacaoGateway).enviarNotificacao(Mockito.argThat(notificacao ->
                notificacao.getTitulo().equals("Encomenda Registrada") &&
                        notificacao.getDestinatario().equals(email) &&
                        notificacao.getMensagem().contains("Caixa com livros")
        ));
    }

    @Test
    void executeDeveLancarExcecaoQuandoMoradorNaoExiste() {
        RegistraEncomendaCommand command = new RegistraEncomendaCommand("Maria", 202, "Envelope");

        Mockito.when(moradorGateway.buscaEmailPorNomeEApartamento("Maria", 202))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(MoradorComNomeEApartamentoException.class, () -> {
            registraEncomendaUseCase.execute(command);
        });

        Mockito.verify(moradorGateway).buscaEmailPorNomeEApartamento("Maria", 202);
        Mockito.verifyNoInteractions(encomendaGateway);
        Mockito.verifyNoInteractions(notificacaoGateway);
    }

}