package com.fiap.gerencia_encomenda.infrastructure.gatewayimpl;

import com.fiap.gerencia_encomenda.application.gateway.MoradorGateway;
import com.fiap.gerencia_encomenda.domain.morador.Morador;
import com.fiap.gerencia_encomenda.infrastructure.persistence.morador.MoradorJpaEntity;
import com.fiap.gerencia_encomenda.infrastructure.persistence.morador.MoradorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MoradorGatewayImpl implements MoradorGateway {

    private final MoradorRepository moradorRepository;

    public MoradorGatewayImpl(MoradorRepository moradorRepository) {
        this.moradorRepository = moradorRepository;
    }

    @Override
    @Transactional
    public String salvar(Morador morador) {
        MoradorJpaEntity moradorJpaEntity = MoradorJpaEntity.fromDomain(morador);
        moradorRepository.save(moradorJpaEntity);
        return "Morador salvo com sucesso!";
    }

    @Override
    public Optional<Morador> buscaPorTelefoneOuEmailOuCpf(String telefone, String email, String cpf) {
        Optional<MoradorJpaEntity> moradorJpaEntityOptional = moradorRepository.findByTelefoneOrEmailOrCpf(telefone, email, cpf);
        return moradorJpaEntityOptional.map(MoradorJpaEntity::toDomain);
    }

    @Override
    public List<Morador> listarMoradores() {
        return moradorRepository.findAll().stream().map(MoradorJpaEntity::toDomain).toList();
    }

    @Override
    public Optional<String> buscaEmailPorNomeEApartamento(String nome, Integer apartamento) {
        return moradorRepository.findEmailByNomeAndApartamento(nome,apartamento);
    }
}
