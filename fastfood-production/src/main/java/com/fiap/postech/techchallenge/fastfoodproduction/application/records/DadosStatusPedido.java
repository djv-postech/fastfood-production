package com.fiap.postech.techchallenge.fastfoodproduction.application.records;


import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.StatusPedido;

public record DadosStatusPedido(String numeroPedido, StatusPedido statusPedido) {
    public DadosStatusPedido(String numeroPedido, StatusPedido statusPedido) {
        this.numeroPedido = numeroPedido;
        this.statusPedido = statusPedido;
    }
}
