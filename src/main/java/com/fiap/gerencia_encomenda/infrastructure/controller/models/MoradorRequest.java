package com.fiap.gerencia_encomenda.infrastructure.controller.models;


public record MoradorRequest(
         String nome,
         String cpf,
         String telefone,
         String email,
         Integer apartamento
) {
}
