package com.fiap.gerencia_encomenda.infrastructure.gatewayimpl;

import com.fiap.gerencia_encomenda.domain.morador.Morador;
import com.fiap.gerencia_encomenda.infrastructure.persistence.morador.MoradorJpaEntity;
import com.fiap.gerencia_encomenda.infrastructure.persistence.morador.MoradorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class MoradorGatewayImplTest {

    @Mock
    private MoradorRepository moradorRepository;

    @InjectMocks
    private MoradorGatewayImpl moradorGateway;

    @Test
    void salvarDevePersistirEMensagemDeSucesso() {
        Morador morador = Morador.instanceOf(UUID.randomUUID(), "João", "123456789", "21999999999", "joao@email.com", 101);
        MoradorJpaEntity entity = MoradorJpaEntity.fromDomain(morador);

        Mockito.when(moradorRepository.save(Mockito.any())).thenReturn(entity);

        String resultado = moradorGateway.salvar(morador);

        Assertions.assertEquals("Morador salvo com sucesso!", resultado);
        Mockito.verify(moradorRepository).save(Mockito.any(MoradorJpaEntity.class));
    }

    @Test
    void buscaPorTelefoneOuEmailOuCpfDeveRetornarMoradorQuandoEncontrado() {
        MoradorJpaEntity entity = new MoradorJpaEntity(); // configure se necessário
        Mockito.when(moradorRepository.findByTelefoneOrEmailOrCpf("21999999999", "joao@email.com", "123456789"))
                .thenReturn(Optional.of(entity));

        Optional<Morador> resultado = moradorGateway.buscaPorTelefoneOuEmailOuCpf("21999999999", "joao@email.com", "123456789");

        Assertions.assertTrue(resultado.isPresent());
        Mockito.verify(moradorRepository).findByTelefoneOrEmailOrCpf("21999999999", "joao@email.com", "123456789");
    }

    @Test
    void listarMoradoresDeveRetornarListaConvertida() {
        List<MoradorJpaEntity> entidades = List.of(new MoradorJpaEntity(), new MoradorJpaEntity());
        Mockito.when(moradorRepository.findAll()).thenReturn(entidades);

        List<Morador> resultado = moradorGateway.listarMoradores();

        Assertions.assertEquals(2, resultado.size());
        Mockito.verify(moradorRepository).findAll();
    }

    @Test
    void buscaEmailPorNomeEApartamentoDeveRetornarEmailQuandoEncontrado() {
        Mockito.when(moradorRepository.findEmailByNomeAndApartamento("João", 101))
                .thenReturn(Optional.of("joao@email.com"));

        Optional<String> resultado = moradorGateway.buscaEmailPorNomeEApartamento("João", 101);

        Assertions.assertTrue(resultado.isPresent());
        Assertions.assertEquals("joao@email.com", resultado.get());
        Mockito.verify(moradorRepository).findEmailByNomeAndApartamento("João", 101);
    }
}
