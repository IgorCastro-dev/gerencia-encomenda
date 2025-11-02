package com.fiap.gerencia_encomenda.domain.morador;

import java.util.Objects;
import java.util.UUID;

public class Morador {
    private UUID id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Integer apartamento;

    private Morador(UUID id,String nome, String cpf, String telefone, String email, Integer apartamento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.apartamento = apartamento;
    }

    public static Morador instanceOf(UUID id,String nome, String cpf, String telefone, String email, Integer apartamento) {
        return new Morador(id,nome, cpf, telefone, email, apartamento);
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Morador morador = (Morador) o;
        return Objects.equals(nome, morador.nome) && Objects.equals(cpf, morador.cpf) && Objects.equals(telefone, morador.telefone) && Objects.equals(email, morador.email) && Objects.equals(apartamento, morador.apartamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cpf, telefone, email, apartamento);
    }
}
