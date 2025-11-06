package com.fiap.gerencia_encomenda.domain.encomenda;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Encomenda {
    private UUID id;
    private String nomeMorador;
    private Integer apartamento;
    private String descricao;
    private LocalDateTime dataRecebimento;
    private LocalDateTime dataEntregue;
    private StatusEncomenda status;

    private Encomenda(UUID id, String nomeMorador, Integer apartamento, String descricao,
                      LocalDateTime dataRecebimento, LocalDateTime dataEntregue, StatusEncomenda status) {
        this.id = id;
        this.nomeMorador = nomeMorador;
        this.apartamento = apartamento;
        this.descricao = descricao;
        this.dataRecebimento = dataRecebimento;
        this.dataEntregue = dataEntregue;
        this.status = status;
    }

    public static Encomenda instanceOf(UUID id, String nomeMorador, Integer apartamento, String descricao) {
        return new Encomenda(id, nomeMorador, apartamento, descricao, null, LocalDateTime.now(), StatusEncomenda.RECEBIDA);
    }

    public static Encomenda instanceOfFull(UUID id, String nomeMorador, Integer apartamento, String descricao, LocalDateTime dataRecebimento, LocalDateTime dataEntregue, StatusEncomenda status) {
        return new Encomenda(id, nomeMorador, apartamento, descricao, dataRecebimento, dataEntregue, status);
    }

    public UUID getId() {
        return id;
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

    public void setStatus(StatusEncomenda status) {
        this.status = status;
    }

    public void setDataRecebimento() {
        this.dataRecebimento = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Encomenda that = (Encomenda) o;
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
        return "Encomenda{" +
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
