package com.fiap.gerencia_encomenda.application.gateway;

import com.fiap.gerencia_encomenda.domain.encomenda.Encomenda;
import com.fiap.gerencia_encomenda.infrastructure.persistence.encomenda.EncomendaJpaEntity;

import java.util.UUID;

public interface EncomendaGateway {
    void salvarEncomenda(Encomenda encomenda);
    EncomendaJpaEntity buscarEncomendaPorId(UUID id);
}
