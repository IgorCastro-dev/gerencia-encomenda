package com.fiap.gerencia_encomenda.domain.notificacao;

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

}
