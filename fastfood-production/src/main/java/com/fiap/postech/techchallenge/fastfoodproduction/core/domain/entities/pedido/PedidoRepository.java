package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido;


import java.util.List;

public interface PedidoRepository {

  Pedido cadastrarPedido(Pedido pedido);

  Pedido atualizarPedido(Pedido pedido);

  Pedido listarPedidoPorNumeroPedido(String numeroPedido);

  List<Pedido> listarPedidosPorStatus(StatusPedido status);

  List<Pedido> listarPedidos();

  void excluirPedido(String numero);
}
