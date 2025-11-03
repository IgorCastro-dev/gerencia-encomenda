package com.fiap.gerencia_encomenda.application.models;

public record EnviaEmailCommand(
        String titulo,
        String destinatario,
        String mensagem
) {
    public static EnviaEmailCommand of(String titulo, String destinatario, String mensagem) {
        return new EnviaEmailCommand(titulo, destinatario, mensagem);
    }
}
