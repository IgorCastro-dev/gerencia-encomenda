package com.fiap.gerencia_encomenda.infrastructure.controller;

import com.fiap.gerencia_encomenda.application.models.RegistraEncomendaCommand;
import com.fiap.gerencia_encomenda.infrastructure.controller.models.EncomendaRequest;
import com.fiap.gerencia_encomenda.usecase.RegistraEncomendaUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/encomenda")
public class EncomendaController {

    private final RegistraEncomendaUseCase registraEncomendaUseCase;

    public EncomendaController(RegistraEncomendaUseCase registraEncomendaUseCase) {
        this.registraEncomendaUseCase = registraEncomendaUseCase;
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registraMorador(@RequestBody EncomendaRequest encomendaRequest) {
        RegistraEncomendaCommand command = RegistraEncomendaCommand.of(
                encomendaRequest.nomeMorador(),
                encomendaRequest.apartamento(),
                encomendaRequest.descricao()
        );
        return ResponseEntity.ok(registraEncomendaUseCase.execute(command));
    }
}
