package com.fiap.gerencia_encomenda.domain.exception;

public class MoradorComNomeEApartamentoException extends RuntimeException {
    public MoradorComNomeEApartamentoException(String nome, Integer apartamento) {
        super("Morador não encontrado: " + nome + " no apartamento " + apartamento);
    }
}
