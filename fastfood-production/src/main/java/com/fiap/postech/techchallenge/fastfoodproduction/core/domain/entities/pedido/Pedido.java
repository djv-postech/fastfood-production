package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.cliente.Cliente;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.Pagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.produto.Produto;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@EqualsAndHashCode
public class Pedido {

  private String numeroPedido;
  private Cliente cliente;
  private List<Produto> produtos;
  private BigDecimal valorTotal;
  private Pagamento pagamento;
  private StatusPedido statusPedido;
  private String qrCode;
  private LocalDateTime dataCriacaoPedido;

  public Pedido(){ }

  public Pedido(
          String numeroPedido,
          Cliente cliente,
          List<Produto> produtos,
          BigDecimal valorTotal,
          Pagamento pagamento,
          StatusPedido statusPedido,
          LocalDateTime dataCriacaoPedido,
          String qrCode) {
    this.numeroPedido = numeroPedido;
    this.cliente = cliente;
    this.produtos = produtos;
    this.valorTotal = valorTotal;
    this.pagamento = pagamento;
    this.statusPedido = statusPedido;
    this.qrCode = qrCode;
    this.dataCriacaoPedido = dataCriacaoPedido;

  }

  public Pedido(
          Cliente cliente,
          List<Produto> produtos,
          BigDecimal valorTotal,
          Pagamento pagamento,
          StatusPedido statusPedido,
          LocalDateTime dataCriacaoPedido) {
    this.cliente = cliente;
    this.produtos = produtos;
    this.valorTotal = valorTotal;
    this.pagamento = pagamento;
    this.statusPedido = statusPedido;
    this.dataCriacaoPedido = dataCriacaoPedido;
  }


  public String getNumeroPedido() {
    return numeroPedido;
  }

  public List<Produto> getProdutos() {
    return produtos;
  }

  public Cliente getCliente() {
    return cliente;
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

  public String getQrCode() {
    return qrCode;
  }

  public void setPagamento(Pagamento pagamento) {
    this.pagamento = pagamento;
  }

  public void setStatus(StatusPedido statusPedido) {
    this.statusPedido = statusPedido;
  }

  public void setQrCode(String qrCode) {
    this.qrCode = qrCode;
  }

  public void setNumeroPedido(String numeroPedido) {
    this.numeroPedido = numeroPedido;
  }

}
