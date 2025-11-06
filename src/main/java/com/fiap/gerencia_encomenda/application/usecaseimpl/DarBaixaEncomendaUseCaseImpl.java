package com.fiap.gerencia_encomenda.application.usecaseimpl;

import com.fiap.gerencia_encomenda.application.gateway.EncomendaGateway;
import com.fiap.gerencia_encomenda.application.models.BaixaEncomendaCommand;
import com.fiap.gerencia_encomenda.domain.encomenda.Encomenda;
import com.fiap.gerencia_encomenda.domain.encomenda.StatusEncomenda;
import com.fiap.gerencia_encomenda.infrastructure.persistence.encomenda.EncomendaJpaEntity;
import com.fiap.gerencia_encomenda.usecase.DarBaixaEncomendaUseCase;

public class DarBaixaEncomendaUseCaseImpl implements DarBaixaEncomendaUseCase {
    private final EncomendaGateway  encomendaGateway;

    public DarBaixaEncomendaUseCaseImpl(EncomendaGateway encomendaGateway) {
        this.encomendaGateway = encomendaGateway;
    }

    @Override
    public String execute(BaixaEncomendaCommand command) {
        EncomendaJpaEntity encomendaJpaEntity = encomendaGateway.buscarEncomendaPorId(command.idEncomenda());
        Encomenda encomenda = EncomendaJpaEntity.toDomain(encomendaJpaEntity);
        encomenda.setStatus(StatusEncomenda.ENTREGUE);
        encomenda.setDataRecebimento();
        encomendaGateway.salvarEncomenda(encomenda);
        return "Baixa na encomenda realizada com sucesso!";
    }
}
