package com.fiap.gerencia_encomenda.infrastructure.controller;

import com.fiap.gerencia_encomenda.application.models.BaixaEncomendaCommand;
import com.fiap.gerencia_encomenda.application.models.RegistraEncomendaCommand;
import com.fiap.gerencia_encomenda.infrastructure.controller.models.EncomendaRequest;
import com.fiap.gerencia_encomenda.usecase.DarBaixaEncomendaUseCase;
import com.fiap.gerencia_encomenda.usecase.RegistraEncomendaUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Tag(name = "Encomenda", description = "API para gerenciamento de encomendas")
@RestController
@RequestMapping("/encomenda")
public class EncomendaController {

    private final RegistraEncomendaUseCase registraEncomendaUseCase;
    private final DarBaixaEncomendaUseCase darBaixaEncomendaUseCase;

    public EncomendaController(RegistraEncomendaUseCase registraEncomendaUseCase,
                               DarBaixaEncomendaUseCase darBaixaEncomendaUseCase) {
        this.registraEncomendaUseCase = registraEncomendaUseCase;
        this.darBaixaEncomendaUseCase = darBaixaEncomendaUseCase;
    }

    @Operation(
            summary = "Registrar nova encomenda",
            description = "Endpoint para registrar uma nova encomenda no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encomenda registrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/registrar")
    public ResponseEntity<String> registraEncomenda(@RequestBody EncomendaRequest encomendaRequest) {
        RegistraEncomendaCommand command = RegistraEncomendaCommand.of(
                encomendaRequest.nomeMorador(),
                encomendaRequest.apartamento(),
                encomendaRequest.descricao()
        );
        return ResponseEntity.ok(registraEncomendaUseCase.execute(command));
    }

    @Operation(
            summary = "Dar baixa em encomenda",
            description = "Endpoint para dar baixa em uma encomenda existente usando seu UUID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Baixa realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Encomenda não encontrada"),
            @ApiResponse(responseCode = "400", description = "UUID inválido"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/baixa/{uuid}")
    public ResponseEntity<String> darBaixaEncomenda(@PathVariable UUID uuid) {
        BaixaEncomendaCommand command = BaixaEncomendaCommand.of(uuid);
        return ResponseEntity.ok(darBaixaEncomendaUseCase.execute(command));
    }
}
