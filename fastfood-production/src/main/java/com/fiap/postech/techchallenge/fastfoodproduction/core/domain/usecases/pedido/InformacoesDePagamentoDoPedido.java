package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.StatusPagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.PedidoRepository;

public class InformacoesDePagamentoDoPedido {

    private final PedidoRepository pedidoRepository;

    public InformacoesDePagamentoDoPedido(PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }

    public StatusPagamento verificaStatusPagamentoPedido(String numeroPedido){
        Pedido pedido = this.pedidoRepository.listarPedidoPorNumeroPedido(numeroPedido);
        return pedido.getPagamento().getStatusPagamento();
    }

}
