package com.fiap.gerencia_encomenda.application.models;

public record RegistraEncomendaCommand(
         String nomeMorador,
         Integer apartamento,
         String descricao
) {
    public static RegistraEncomendaCommand of(String nomeMorador, Integer apartamento, String descricao) {
        return new RegistraEncomendaCommand(nomeMorador, apartamento, descricao);
    }
}
