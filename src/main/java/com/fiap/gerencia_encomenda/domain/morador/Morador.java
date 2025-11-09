package com.fiap.gerencia_encomenda.domain.morador;

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
}
