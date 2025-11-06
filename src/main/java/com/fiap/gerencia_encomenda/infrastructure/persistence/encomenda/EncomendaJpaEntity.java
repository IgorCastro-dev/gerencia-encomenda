package com.fiap.gerencia_encomenda.infrastructure.persistence.encomenda;

import com.fiap.gerencia_encomenda.domain.encomenda.Encomenda;
import com.fiap.gerencia_encomenda.domain.encomenda.StatusEncomenda;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "encomendas")
public class EncomendaJpaEntity {

    @Id
    private UUID id;

    @Column(name = "nome_morador", nullable = false, length = 255)
    private String nomeMorador;

    @Column(name = "apartamento", nullable = false)
    private Integer apartamento;

    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;

    @Column(name = "data_recebimento")
    private LocalDateTime dataRecebimento;

    @Column(name = "data_entregue")
    private LocalDateTime dataEntregue;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusEncomenda status;

    public EncomendaJpaEntity() {}

    private EncomendaJpaEntity(UUID id, String nomeMorador, Integer apartamento, String descricao, LocalDateTime dataRecebimento, LocalDateTime dataEntregue, StatusEncomenda status) {
        this.id = id;
        this.nomeMorador = nomeMorador;
        this.apartamento = apartamento;
        this.descricao = descricao;
        this.dataRecebimento = dataRecebimento;
        this.dataEntregue = dataEntregue;
        this.status = status;
    }

    public static EncomendaJpaEntity instanceOf(UUID id, String nomeMorador, Integer apartamento, String descricao, LocalDateTime dataRecebimento, LocalDateTime dataEntregue, StatusEncomenda status) {
        return new EncomendaJpaEntity(id, nomeMorador, apartamento, descricao, dataRecebimento, dataEntregue, status);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeMorador() {
        return nomeMorador;
    }

    public Integer getApartamento() {
        return apartamento;
    }

    public String getDescricao() {
        return descricao;
    }


    public LocalDateTime getDataRecebimento() {
        return dataRecebimento;
    }

    public LocalDateTime getDataEntregue() {
        return dataEntregue;
    }

    public StatusEncomenda getStatus() {
        return status;
    }

    public static Encomenda toDomain(EncomendaJpaEntity encomendaJpaEntity) {
        return Encomenda.instanceOfFull(
                encomendaJpaEntity.getId(),
                encomendaJpaEntity.getNomeMorador(),
                encomendaJpaEntity.getApartamento(),
                encomendaJpaEntity.getDescricao(),
                encomendaJpaEntity.getDataEntregue(),
                encomendaJpaEntity.getDataRecebimento(),
                encomendaJpaEntity.getStatus()
        );
    }

    public static EncomendaJpaEntity fromDomain(Encomenda encomenda) {
        EncomendaJpaEntity entity = new EncomendaJpaEntity(
                encomenda.getId(),
                encomenda.getNomeMorador(),
                encomenda.getApartamento(),
                encomenda.getDescricao(),
                encomenda.getDataEntregue(),
                encomenda.getDataRecebimento(),
                encomenda.getStatus()
        );
        return entity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EncomendaJpaEntity that = (EncomendaJpaEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nomeMorador, that.nomeMorador) &&
                Objects.equals(apartamento, that.apartamento) &&
                Objects.equals(descricao, that.descricao) &&
                Objects.equals(dataRecebimento, that.dataRecebimento) &&
                Objects.equals(dataEntregue, that.dataEntregue) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeMorador, apartamento, descricao, dataRecebimento, dataEntregue, status);
    }

    @Override
    public String toString() {
        return "EncomendaJpaEntity{" +
                "id=" + id +
                ", nomeMorador='" + nomeMorador + '\'' +
                ", apartamento=" + apartamento +
                ", descricao='" + descricao + '\'' +
                ", dataRecebimento=" + dataRecebimento +
                ", dataEntregue=" + dataEntregue +
                ", status=" + status +
                '}';
    }
}