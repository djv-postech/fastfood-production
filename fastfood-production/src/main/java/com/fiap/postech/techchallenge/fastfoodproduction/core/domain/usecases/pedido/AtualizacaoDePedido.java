package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.Pagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.StatusPagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.PedidoRepository;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.StatusPedido;
import org.springframework.transaction.annotation.Transactional;

import static com.fiap.postech.techchallenge.fastfoodproduction.infra.persistence.repository.converter.NotificacaoConverter.criarNotificacao;

public class AtualizacaoDePedido {

  private final PedidoRepository pedidoRepository;
  private final NotificacaoService notificacaoService;

  public AtualizacaoDePedido(PedidoRepository pedidoRepository,
                             NotificacaoService notificacaoService) {
    this.pedidoRepository = pedidoRepository;
    this.notificacaoService = notificacaoService;
  }

  @Transactional
  public Pedido atualizarStatusPedido(String numeroPedido, StatusPedido statusPedido) {
    Pedido pedido = pedidoRepository.listarPedidoPorNumeroPedido(numeroPedido);
    pedido.setStatus(statusPedido);
    Pedido pedidoAtualizado = pedidoRepository.atualizarPedido(pedido);

    notificacaoService.enviarParaFilaDeNotificacao(criarNotificacao(numeroPedido, pedido.getCliente(), statusPedido,
            pedido.getPagamento()));

    return pedidoAtualizado;
  }

  @Transactional
  public Pedido atualizarStatusPagamentoPedido(String numeroPedido, StatusPagamento statusPagamento) {
    final Pedido pedido = pedidoRepository.listarPedidoPorNumeroPedido(numeroPedido);
    Pagamento pagamento = pedido.getPagamento();
    pagamento.setStatusPagamento(statusPagamento);
    StatusPedido statusPedido =
            statusPagamento.equals(StatusPagamento.APROVADO)
            ? StatusPedido.EM_PREPARACAO
            : pedido.getStatusPedido();
    pedido.setPagamento(pagamento);
    pedido.setStatus(statusPedido);

    Pedido pedidoAtualizado = pedidoRepository.atualizarPedido(pedido);

    notificacaoService.enviarParaFilaDeNotificacao(criarNotificacao(numeroPedido, pedido.getCliente(), statusPedido,
            pedido.getPagamento()));

    return pedidoAtualizado;
  }
}
