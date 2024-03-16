package com.fiap.postech.techchallenge.fastfoodproduction.application.records;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.notificacao.Notificacao;

public record DadosNotificacao (

     String nome,
     String email,
     String texto) {

    public DadosNotificacao(Notificacao notificacao) {
        this(notificacao.getNome(), notificacao.getEmail(), notificacao.getTexto());

    }
}