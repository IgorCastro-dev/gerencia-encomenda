package com.fiap.gerencia_encomenda.infrastructure.gatewayimpl;

import com.fiap.gerencia_encomenda.application.gateway.EncomendaGateway;
import com.fiap.gerencia_encomenda.domain.encomenda.Encomenda;
import com.fiap.gerencia_encomenda.infrastructure.persistence.encomenda.EncomendaJpaEntity;
import com.fiap.gerencia_encomenda.infrastructure.persistence.encomenda.EncomendaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EncomendaGatewayImpl implements EncomendaGateway {
    private final EncomendaRepository  encomendaRepository;

    public EncomendaGatewayImpl(EncomendaRepository encomendaRepository) {
        this.encomendaRepository = encomendaRepository;
    }

    @Override
    public void salvarEncomenda(Encomenda encomenda) {
        encomendaRepository.save(EncomendaJpaEntity.fromDomain(encomenda));
    }

    @Override
    public EncomendaJpaEntity buscarEncomendaPorId(UUID id) {
        return encomendaRepository.getReferenceById(id);
    }
}
