package com.fiap.gerencia_encomenda.infrastructure.persistence.encomenda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EncomendaRepository extends JpaRepository<EncomendaJpaEntity, UUID> {
}
