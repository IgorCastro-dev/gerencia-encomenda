package com.fiap.gerencia_encomenda.infrastructure.persistence.morador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MoradorRepository extends JpaRepository<MoradorJpaEntity, UUID> {
    Optional<MoradorJpaEntity> findByTelefoneOrEmailOrCpf(String telefone, String email, String cpf);

    @Query("SELECT m.email FROM MoradorJpaEntity m WHERE m.nome = :nome AND m.apartamento = :apartamento")
    Optional<String> findEmailByNomeAndApartamento(@Param("nome") String nome, @Param("apartamento") Integer apartamento);
}
