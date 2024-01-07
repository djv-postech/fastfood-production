package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.Pagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.StatusPagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.PedidoRepository;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.StatusPedido;

public class AtualizacaoDePedido {

  private final PedidoRepository pedidoRepository;

  public AtualizacaoDePedido(PedidoRepository pedidoRepository) {
    this.pedidoRepository = pedidoRepository;
  }

  public Pedido atualizarPedido(String numeroPedido, StatusPedido statusPedido) {
    final Pedido pedido = pedidoRepository.listarPedidoPorNumeroPedido(numeroPedido);
    pedido.setStatus(statusPedido);
    return pedidoRepository.atualizarPedido(pedido);
  }

  public Pedido atualizarPedido(String numeroPedido, Pagamento pagamento) {
    final Pedido pedido = pedidoRepository.listarPedidoPorNumeroPedido(numeroPedido);
    StatusPedido statusPedido =
        pagamento.getStatusPagamento().equals(StatusPagamento.APROVADO)
            ? StatusPedido.EM_PREPARACAO
            : pedido.getStatusPedido();
    pedido.setPagamento(pagamento);
    pedido.setStatus(statusPedido);
    return pedidoRepository.atualizarPedido(pedido);
  }
}
