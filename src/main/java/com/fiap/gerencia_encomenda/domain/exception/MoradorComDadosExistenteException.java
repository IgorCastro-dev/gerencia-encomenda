package com.fiap.gerencia_encomenda.domain.exception;

public class MoradorComDadosExistenteException extends RuntimeException {
    public MoradorComDadosExistenteException(String telefone, String email, String cpf) {
        super("Já existe um morador com um ou mais dos seguintes dados: " +
                "Telefone: " + telefone + ", " +
                "Email: " + email + ", " +
                "CPF: " + cpf);
    }
}
