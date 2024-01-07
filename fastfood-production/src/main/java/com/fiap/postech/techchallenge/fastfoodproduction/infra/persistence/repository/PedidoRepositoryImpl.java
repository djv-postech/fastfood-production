package com.fiap.postech.techchallenge.fastfoodproduction.infra.persistence.repository;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.PedidoRepository;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.StatusPedido;
import com.fiap.postech.techchallenge.fastfoodproduction.infra.persistence.exception.NotFoundException;
import com.fiap.postech.techchallenge.fastfoodproduction.infra.persistence.repository.converter.PedidoConverter;
import com.fiap.postech.techchallenge.fastfoodproduction.infra.persistence.repository.entity.PedidoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PedidoRepositoryImpl implements PedidoRepository {

  private final PedidoRepositoryMongo pedidoRepositoryMongo;
  private final PedidoConverter pedidoConverter;

  @Override
  public Pedido cadastrarPedido(Pedido pedido) {
    PedidoEntity pedidoEntity = pedidoRepositoryMongo.insert(new PedidoEntity(pedido));
    return pedidoConverter.convertFrom(pedidoEntity);
  }

  @Override
  public Pedido atualizarPedido(Pedido pedido) {
    PedidoEntity pedidoEntity = pedidoRepositoryMongo.save(new PedidoEntity(pedido));
    return pedidoConverter.convertFrom(pedidoEntity);
  }

  @Override
  public Pedido listarPedidoPorNumeroPedido(String numeroPedido) {
    return pedidoRepositoryMongo.findById(numeroPedido)
            .map(pedidoConverter::convertFrom)
            .orElseThrow(() -> new NotFoundException("Pedido de Id: "+ numeroPedido +" não encontrado"));
  }

  @Override
  public List<Pedido> listarPedidosPorStatus(StatusPedido status) {
    return pedidoRepositoryMongo.findByStatusPedido(status).stream().map(pedidoConverter::convertFrom).collect(
            Collectors.toList());
  }

  @Override
  public List<Pedido> listarPedidos() {
    return pedidoRepositoryMongo.findAll()
            .stream()
            .map(pedidoConverter::convertFrom)
            .collect(Collectors.toList());
  }

  @Override
  public void excluirPedido(String numero) {
    this.pedidoRepositoryMongo.deleteById(numero);
  }

}
