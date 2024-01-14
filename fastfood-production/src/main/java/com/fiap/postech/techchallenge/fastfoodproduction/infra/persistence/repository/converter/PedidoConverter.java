package com.fiap.postech.techchallenge.fastfoodproduction.infra.persistence.repository.converter;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodproduction.infra.persistence.repository.entity.PedidoEntity;
import org.springframework.stereotype.Component;

@Component
public class PedidoConverter {

  public Pedido convertFrom(PedidoEntity pedidoEntity) {
    return new Pedido(
        pedidoEntity.getNumeroPedido(),
        pedidoEntity.getCliente(),
        pedidoEntity.getProdutos(),
        pedidoEntity.getValorTotal(),
        pedidoEntity.getPagamento(),
        pedidoEntity.getStatusPedido(),
        pedidoEntity.getDataCriacaoPedido());
  }

  public PedidoEntity convertToEntity(Pedido pedido) {
    return new PedidoEntity(pedido.getCliente(), pedido.getProdutos(), pedido.getValorTotal(), pedido.getPagamento(), pedido.getStatusPedido(),
            pedido.getDataCriacaoPedido());
  }
}
