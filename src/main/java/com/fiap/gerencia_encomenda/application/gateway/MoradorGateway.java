package com.fiap.gerencia_encomenda.application.gateway;

import com.fiap.gerencia_encomenda.domain.morador.Morador;

import java.util.List;
import java.util.Optional;

public interface MoradorGateway {
    String salvar(Morador morador);
    Optional<Morador> buscaPorTelefoneOuEmailOuCpf(String telefone, String email, String cpf);
    List<Morador> listarMoradores();
    Optional<String> buscaEmailPorNomeEApartamento(String nome, Integer apartamento);
}
