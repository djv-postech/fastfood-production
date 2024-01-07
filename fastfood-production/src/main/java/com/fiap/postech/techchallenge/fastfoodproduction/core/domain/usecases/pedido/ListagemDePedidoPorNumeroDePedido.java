package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.PedidoRepository;

public class ListagemDePedidoPorNumeroDePedido {

    private final PedidoRepository pedidoRepository;

    public ListagemDePedidoPorNumeroDePedido(PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido listarPedidoPorNumeroPedido(String idPedido) {
        return this.pedidoRepository.listarPedidoPorNumeroPedido(idPedido);
    }

}
