package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.notificacao;

public class Notificacao {
    private  String nome;
    private  String email;
    private  String texto;

    public Notificacao( String texto) {
        this.texto = texto;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTexto() {
        return texto;
    }
}
