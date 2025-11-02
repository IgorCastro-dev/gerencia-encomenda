package com.fiap.gerencia_encomenda.infrastructure.controller;

import com.fiap.gerencia_encomenda.application.models.CadastraMoradorCommand;
import com.fiap.gerencia_encomenda.domain.morador.Morador;
import com.fiap.gerencia_encomenda.infrastructure.controller.models.MoradorRequest;
import com.fiap.gerencia_encomenda.usecase.CadastraMoradorUseCase;
import com.fiap.gerencia_encomenda.usecase.ListaMoradoresUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/morador")
public class MoradorController {

    private final CadastraMoradorUseCase cadastraMoradorUseCase;
    private final ListaMoradoresUseCase listaMoradoresUseCase;

    public MoradorController(
            CadastraMoradorUseCase cadastraMoradorUseCase,
            ListaMoradoresUseCase listaMoradoresUseCase
    ) {
        this.cadastraMoradorUseCase = cadastraMoradorUseCase;
        this.listaMoradoresUseCase = listaMoradoresUseCase;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastraMorador(@RequestBody MoradorRequest moradorRequest) {
        CadastraMoradorCommand command = CadastraMoradorCommand.of(
                moradorRequest.nome(),
                moradorRequest.cpf(),
                moradorRequest.telefone(),
                moradorRequest.email(),
                moradorRequest.apartamento()
                );
        return ResponseEntity.ok(cadastraMoradorUseCase.execute(command));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Morador>> listarMoradores() {
        return ResponseEntity.ok(listaMoradoresUseCase.execute());
    }
}
