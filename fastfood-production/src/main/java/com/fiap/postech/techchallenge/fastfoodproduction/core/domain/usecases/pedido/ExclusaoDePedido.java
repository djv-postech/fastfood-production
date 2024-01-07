package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.PedidoRepository;

public class ExclusaoDePedido {

  private final PedidoRepository pedidoRepository;

  public ExclusaoDePedido(PedidoRepository pedidoRepository) {
    this.pedidoRepository = pedidoRepository;
  }

  public void excluirPedido(String numeroPedido) {
    pedidoRepository.excluirPedido(numeroPedido);
  }
}
