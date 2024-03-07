package com.fiap.postech.techchallenge.fastfoodproduction.application.records;

public class DadosNotificacao {

    private String nome;
    private String email;
    private String texto;

    public DadosNotificacao(String nome, String email, String texto) {
        this.nome = nome;
        this.email = email;
        this.texto = texto;
    }

    public DadosNotificacao(String texto) {
        this.texto = texto;
    }

}
