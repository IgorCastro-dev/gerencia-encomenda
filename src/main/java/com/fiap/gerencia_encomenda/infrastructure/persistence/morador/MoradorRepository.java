package com.fiap.gerencia_encomenda.infrastructure.persistence.morador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MoradorRepository extends JpaRepository<MoradorJpaEntity, UUID> {
    Optional<MoradorJpaEntity> findByTelefoneOrEmailOrCpf(String telefone, String email, String cpf);
}
