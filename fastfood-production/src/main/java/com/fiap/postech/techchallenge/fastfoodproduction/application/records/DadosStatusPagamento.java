package com.fiap.postech.techchallenge.fastfoodproduction.application.records;


import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.StatusPagamento;

public record DadosStatusPagamento(String numeroPedido, StatusPagamento statusPagamento) {
    public DadosStatusPagamento(String numeroPedido, StatusPagamento statusPagamento) {
        this.numeroPedido = numeroPedido;
        this.statusPagamento = statusPagamento;
    }
}
