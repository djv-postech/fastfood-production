package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido;

public enum StatusPedido {

    PRONTO("pronto"),
    EM_PREPARACAO("em preparação"),
    RECEBIDO("recebido"),
    CANCELADO("cancelado"),
    FINALIZADO("finalizado");


    private final String value;

    StatusPedido(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
