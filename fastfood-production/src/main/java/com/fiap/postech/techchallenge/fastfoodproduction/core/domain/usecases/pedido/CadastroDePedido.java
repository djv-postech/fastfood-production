package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.PedidoRepository;

public class CadastroDePedido {

    private final PedidoRepository pedidoRepository;

    public CadastroDePedido(PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido cadastrarPedido(Pedido pedido){
        return pedidoRepository.cadastrarPedido(pedido);
    }
}
