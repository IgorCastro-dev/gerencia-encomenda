package com.fiap.gerencia_encomenda.infrastructure.controller;

import com.fiap.gerencia_encomenda.application.models.BaixaEncomendaCommand;
import com.fiap.gerencia_encomenda.application.models.RegistraEncomendaCommand;
import com.fiap.gerencia_encomenda.infrastructure.controller.models.EncomendaRequest;
import com.fiap.gerencia_encomenda.usecase.DarBaixaEncomendaUseCase;
import com.fiap.gerencia_encomenda.usecase.RegistraEncomendaUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @PostMapping("/registrar")
    public ResponseEntity<String> registraEncomenda(@RequestBody EncomendaRequest encomendaRequest) {
        RegistraEncomendaCommand command = RegistraEncomendaCommand.of(
                encomendaRequest.nomeMorador(),
                encomendaRequest.apartamento(),
                encomendaRequest.descricao()
        );
        return ResponseEntity.ok(registraEncomendaUseCase.execute(command));
    }

    @GetMapping("/baixa/{uuid}")
    public ResponseEntity<String> darBaixaEncomenda(@PathVariable UUID uuid) {
        BaixaEncomendaCommand command = BaixaEncomendaCommand.of(uuid);
        return ResponseEntity.ok(darBaixaEncomendaUseCase.execute(command));
    }
}
