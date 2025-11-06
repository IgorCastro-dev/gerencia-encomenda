package com.fiap.gerencia_encomenda.application.models;

import java.util.UUID;

public record BaixaEncomendaCommand(
        UUID idEncomenda
) {
    public static BaixaEncomendaCommand of(UUID idEncomenda) {
        return new BaixaEncomendaCommand(idEncomenda);
    }
}
