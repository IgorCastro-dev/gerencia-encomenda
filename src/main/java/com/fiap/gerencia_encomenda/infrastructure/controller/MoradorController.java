package com.fiap.gerencia_encomenda.infrastructure.controller;

import com.fiap.gerencia_encomenda.application.models.CadastraMoradorCommand;
import com.fiap.gerencia_encomenda.domain.morador.Morador;
import com.fiap.gerencia_encomenda.infrastructure.controller.models.MoradorRequest;
import com.fiap.gerencia_encomenda.usecase.CadastraMoradorUseCase;
import com.fiap.gerencia_encomenda.usecase.ListaMoradoresUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Morador", description = "API para gerenciamento de moradores do condomínio")
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


    @Operation(
            summary = "Cadastrar novo morador",
            description = "Endpoint para cadastrar um novo morador no sistema do condomínio"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Morador cadastrado com sucesso",
                    content = @Content(schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos fornecidos"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Morador já cadastrado"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor"
            )
    })
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

    @Operation(
            summary = "Listar todos os moradores",
            description = "Endpoint para listar todos os moradores cadastrados no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de moradores recuperada com sucesso",
                    content = @Content(schema = @Schema(implementation = Morador.class))
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Nenhum morador cadastrado"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor"
            )
    })
    @GetMapping("/listar")
    public ResponseEntity<List<Morador>> listarMoradores() {
        return ResponseEntity.ok(listaMoradoresUseCase.execute());
    }
}
