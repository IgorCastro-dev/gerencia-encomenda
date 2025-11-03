package com.fiap.gerencia_encomenda.infrastructure.persistence.encomenda;

import com.fiap.gerencia_encomenda.domain.encomenda.Encomenda;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "encomendas")
public class EncomendaJpaEntity {

    @Id
    private UUID id;

    @Column(name = "nome_morador", nullable = false, length = 255)
    private String nomeMorador;

    @Column(name = "apartamento", nullable = false)
    private Integer apartamento;

    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;


    public EncomendaJpaEntity() {}

    private EncomendaJpaEntity(UUID id, String nomeMorador, Integer apartamento, String descricao) {
        this.id = id;
        this.nomeMorador = nomeMorador;
        this.apartamento = apartamento;
        this.descricao = descricao;
    }

    public static EncomendaJpaEntity instanceOf(UUID id, String nomeMorador, Integer apartamento, String descricao) {
        return new EncomendaJpaEntity(id, nomeMorador, apartamento, descricao);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeMorador() {
        return nomeMorador;
    }

    public void setNomeMorador(String nomeMorador) {
        this.nomeMorador = nomeMorador;
    }

    public Integer getApartamento() {
        return apartamento;
    }

    public void setApartamento(Integer apartamento) {
        this.apartamento = apartamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static EncomendaJpaEntity fromDomain(Encomenda encomenda) {
        return new EncomendaJpaEntity(
                encomenda.getId(),
                encomenda.getNomeMorador(),
                encomenda.getApartamento(),
                encomenda.getDescricao()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EncomendaJpaEntity encomenda = (EncomendaJpaEntity) o;
        return Objects.equals(id, encomenda.id) &&
                Objects.equals(nomeMorador, encomenda.nomeMorador) &&
                Objects.equals(apartamento, encomenda.apartamento) &&
                Objects.equals(descricao, encomenda.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeMorador, apartamento, descricao);
    }

    @Override
    public String toString() {
        return "EncomendaJpaEntity{" +
                "id=" + id +
                ", nomeMorador='" + nomeMorador + '\'' +
                ", apartamento=" + apartamento +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
