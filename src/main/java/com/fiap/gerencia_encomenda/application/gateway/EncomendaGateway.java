package com.fiap.gerencia_encomenda.application.gateway;

import com.fiap.gerencia_encomenda.domain.encomenda.Encomenda;

public interface EncomendaGateway {
    void salvarEncomenda(Encomenda encomenda);
}
