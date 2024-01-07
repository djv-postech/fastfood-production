package com.fiap.postech.techchallenge.fastfoodproduction.application.records;


import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.StatusPagamento;

public record StatusPagamentoPedido(String numeroPedido, StatusPagamento statusPagamento) {


}
