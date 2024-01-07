package com.fiap.postech.techchallenge.fastfoodproduction.infra.persistence.repository.entity;

import java.math.BigDecimal;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.cliente.Cliente;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.Pagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.StatusPedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.produto.Produto;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Document(collection = "pedidos")
public class PedidoEntity {

  @Id
  private String numeroPedido;
  private Cliente cliente;
  private List<Produto> produtos;
  private BigDecimal valorTotal;
  private Pagamento pagamento;
  private StatusPedido statusPedido;
  private LocalDateTime dataCriacaoPedido;

  public PedidoEntity(Pedido pedido) {
    this.numeroPedido = pedido.getNumeroPedido();
    this.cliente = pedido.getCliente();
    this.produtos = pedido.getProdutos();
    this.valorTotal = pedido.getValorTotal();
    this.pagamento = pedido.getPagamento();
    this.statusPedido = pedido.getStatusPedido();
    this.dataCriacaoPedido = pedido.getDataCriacaoPedido();
  }

  public PedidoEntity(
      String numeroPedido,
      Cliente cliente,
      List<Produto> produtos,
      BigDecimal valorTotal,
      Pagamento pagamento,
      StatusPedido statusPedido,
      LocalDateTime dataCriacaoPedido) {
    this.numeroPedido = numeroPedido;
    this.cliente = cliente;
    this.produtos = produtos;
    this.valorTotal = valorTotal;
    this.pagamento = pagamento;
    this.statusPedido = statusPedido;
    this.dataCriacaoPedido = dataCriacaoPedido;
  }

  public PedidoEntity() {}

  public String getNumeroPedido() {
    return numeroPedido;
  }

  public List<Produto> getProdutos() {
    return produtos;
  }

  public BigDecimal getValorTotal() {
    return valorTotal;
  }

  public Pagamento getPagamento() {
    return pagamento;
  }

  public StatusPedido getStatusPedido() {
    return statusPedido;
  }

  public LocalDateTime getDataCriacaoPedido() {
    return dataCriacaoPedido;
  }

  public Cliente getCliente() {
    return cliente;
  }
}
