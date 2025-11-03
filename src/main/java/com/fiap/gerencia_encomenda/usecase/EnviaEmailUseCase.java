package com.fiap.gerencia_encomenda.usecase;

import com.fiap.gerencia_encomenda.application.models.EnviaEmailCommand;
import com.resend.core.exception.ResendException;

public interface EnviaEmailUseCase {
    void execute(EnviaEmailCommand command) throws ResendException;
}
