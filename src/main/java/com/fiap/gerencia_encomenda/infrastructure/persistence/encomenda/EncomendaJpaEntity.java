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

    public static EncomendaJpaEntity instanceOf(UUID id, String nomeMorador, Integer apartamento,
                                                String descricao, LocalDateTime dataRecebimento,
                                                LocalDateTime dataEntregue, StatusEncomenda status) {
        EncomendaJpaEntity entity = new EncomendaJpaEntity();
        entity.setId(id);
        entity.setNomeMorador(nomeMorador);
        entity.setApartamento(apartamento);
        entity.setDescricao(descricao);
        entity.setDataRecebimento(dataRecebimento);
        entity.setDataEntregue(dataEntregue);
        entity.setStatus(status);
        return entity;
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

    public void setNomeMorador(String nomeMorador) {
        this.nomeMorador = nomeMorador;
    }

    public void setApartamento(Integer apartamento) {
        this.apartamento = apartamento;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataRecebimento(LocalDateTime dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public void setDataEntregue(LocalDateTime dataEntregue) {
        this.dataEntregue = dataEntregue;
    }

    public void setStatus(StatusEncomenda status) {
        this.status = status;
    }

    public static Encomenda toDomain(EncomendaJpaEntity encomendaJpaEntity) {
        return Encomenda.instanceOfFull(
                encomendaJpaEntity.getId(),
                encomendaJpaEntity.getNomeMorador(),
                encomendaJpaEntity.getApartamento(),
                encomendaJpaEntity.getDescricao(),
                encomendaJpaEntity.getDataRecebimento(),
                encomendaJpaEntity.getDataEntregue(),
                encomendaJpaEntity.getStatus()
        );
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