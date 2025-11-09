package com.fiap.gerencia_encomenda.infrastructure.mappers;

import com.fiap.gerencia_encomenda.domain.encomenda.Encomenda;
import com.fiap.gerencia_encomenda.infrastructure.persistence.encomenda.EncomendaJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class EncomendaMapper {
    public Encomenda toDomain(EncomendaJpaEntity entity) {
        if (entity == null) {
            return null;
        }

        return Encomenda.instanceOfFull(
                entity.getId(),
                entity.getNomeMorador(),
                entity.getApartamento(),
                entity.getDescricao(),
                entity.getDataRecebimento(),
                entity.getDataEntregue(),
                entity.getStatus()
        );
    }
    public EncomendaJpaEntity fromDomain(Encomenda encomenda) {
        if (encomenda == null) {
            return null;
        }

        EncomendaJpaEntity entity = new EncomendaJpaEntity();
        entity.setId(encomenda.getId());
        entity.setNomeMorador(encomenda.getNomeMorador());
        entity.setApartamento(encomenda.getApartamento());
        entity.setDescricao(encomenda.getDescricao());
        entity.setDataRecebimento(encomenda.getDataRecebimento());
        entity.setDataEntregue(encomenda.getDataEntregue());
        entity.setStatus(encomenda.getStatus());

        return entity;
    }
}
