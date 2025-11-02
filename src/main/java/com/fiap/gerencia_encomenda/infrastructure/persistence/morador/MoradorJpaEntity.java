package com.fiap.gerencia_encomenda.infrastructure.persistence.morador;

import com.fiap.gerencia_encomenda.domain.morador.Morador;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(
        name = "morador",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "cpf", name = "uk_morador_cpf"),
                @UniqueConstraint(columnNames = "telefone", name = "uk_morador_telefone"),
                @UniqueConstraint(columnNames = "email", name = "uk_morador_email")
        }
)
public class MoradorJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, unique = true, length = 15)
    private String telefone;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private Integer apartamento;

    public MoradorJpaEntity() {
    }

    public MoradorJpaEntity(UUID id, String nome, String cpf, String telefone,
                            String email, Integer apartamento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.apartamento = apartamento;
    }

    public UUID getId() {
        return id;
    }

    public Integer getApartamento() {
        return apartamento;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public static MoradorJpaEntity fromDomain(Morador morador) {
        return new MoradorJpaEntity(
                morador.getId(),
                morador.getNome(),
                morador.getCpf(),
                morador.getTelefone(),
                morador.getEmail(),
                morador.getApartamento()
        );
    }

    public Morador toDomain() {
        return Morador.instanceOf(
                this.id,
                this.nome,
                this.cpf,
                this.telefone,
                this.email,
                this.apartamento
        );
    }

    public static Optional<Morador> toDomain(Optional<MoradorJpaEntity> jpaEntity) {
        return jpaEntity.map(MoradorJpaEntity::toDomain);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoradorJpaEntity)) return false;
        MoradorJpaEntity that = (MoradorJpaEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "MoradorJpaEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", apartamento=" + apartamento +
                '}';
    }
}
