package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.PedidoRepository;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.StatusPedido;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListagemDePedidoOrdenadosPorRecebimentoEStatus {

  private final PedidoRepository pedidoRepository;

  public ListagemDePedidoOrdenadosPorRecebimentoEStatus(PedidoRepository pedidoRepository) {
    this.pedidoRepository = pedidoRepository;
  }

  public List<Pedido> listarPedidosOrdenadosPorRecebimentoEStatus() {
    return this.pedidoRepository.listarPedidos().stream()
        .filter(pedido -> pedido.getStatusPedido() != StatusPedido.FINALIZADO)
        .sorted(Comparator.comparing(Pedido::getStatusPedido))
        .sorted(Comparator.comparing(Pedido::getDataCriacaoPedido).reversed())
        .collect(Collectors.toList());
  }
}
