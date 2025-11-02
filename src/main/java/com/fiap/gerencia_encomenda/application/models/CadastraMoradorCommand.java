package com.fiap.gerencia_encomenda.application.models;

import java.util.Objects;

public record CadastraMoradorCommand(
        String nome,
        String cpf,
        String telefone,
        String email,
        Integer apartamento
) {
    public static CadastraMoradorCommand of(String nome, String cpf, String telefone, String email, Integer apartamento) {
        return new CadastraMoradorCommand(nome, cpf, telefone, email, apartamento);
    }
}
