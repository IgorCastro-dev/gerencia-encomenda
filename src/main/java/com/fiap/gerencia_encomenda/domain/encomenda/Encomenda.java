package com.fiap.gerencia_encomenda.domain.encomenda;

import java.util.Objects;
import java.util.UUID;

public class Encomenda {
    private UUID id;
    private String nomeMorador;
    private Integer apartamento;
    private String descricao;

    private Encomenda(UUID id, String nomeMorador, Integer apartamento, String descricao) {
        this.id = id;
        this.nomeMorador = nomeMorador;
        this.apartamento = apartamento;
        this.descricao = descricao;
    }
    public static Encomenda instanceOf(UUID id, String nomeMorador, Integer apartamento, String descricao) {
        return new Encomenda(id, nomeMorador, apartamento, descricao);
    }

    public UUID getId() {
        return id;
    }

    public String getNomeMorador() {
        return nomeMorador;
    }

    public Integer getApartamento() {
        return apartamento;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Encomenda encomenda = (Encomenda) o;
        return Objects.equals(id, encomenda.id) && Objects.equals(nomeMorador, encomenda.nomeMorador) && Objects.equals(apartamento, encomenda.apartamento) && Objects.equals(descricao, encomenda.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeMorador, apartamento, descricao);
    }
}
