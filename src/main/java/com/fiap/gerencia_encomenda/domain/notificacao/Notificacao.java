package com.fiap.gerencia_encomenda.domain.notificacao;

import java.util.Objects;

public class Notificacao {
    private String titulo;
    private String destinatario;
    private String mensagem;

    public Notificacao(String titulo, String destinatario, String mensagem) {
        this.titulo = titulo;
        this.destinatario = destinatario;
        this.mensagem = mensagem;
    }

    public static Notificacao instanceOf(String titulo, String destinatario, String mensagem) {
        return new Notificacao(titulo, destinatario, mensagem);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getMensagem() {
        return mensagem;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Notificacao that = (Notificacao) o;
        return Objects.equals(titulo, that.titulo) && Objects.equals(destinatario, that.destinatario) && Objects.equals(mensagem, that.mensagem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, destinatario, mensagem);
    }
}
