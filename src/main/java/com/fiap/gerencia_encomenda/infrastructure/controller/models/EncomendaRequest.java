package com.fiap.gerencia_encomenda.infrastructure.controller.models;

public record EncomendaRequest(
         String nomeMorador,
         Integer apartamento,
         String descricao
) {
}
