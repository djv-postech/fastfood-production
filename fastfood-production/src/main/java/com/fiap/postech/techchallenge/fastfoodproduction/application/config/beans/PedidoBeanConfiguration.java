package com.fiap.postech.techchallenge.fastfoodproduction.application.config.beans;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.PedidoRepository;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoBeanConfiguration {

  private final PedidoRepository pedidoRepository;

  public PedidoBeanConfiguration(PedidoRepository pedidoRepository) {
    this.pedidoRepository = pedidoRepository;
  }

  @Bean
  public CadastroDePedido cadastroDePedido() {
    return new CadastroDePedido(pedidoRepository);
  }

  @Bean
  public AtualizacaoDePedido atualizacaoDePedido() {
    return new AtualizacaoDePedido(pedidoRepository);
  }

  @Bean
  public ListagemDePedidoPorNumeroDePedido listagemDePedidoPorNumeroDePedido() {
    return new ListagemDePedidoPorNumeroDePedido(pedidoRepository);
  }

  @Bean
  public ListagemDePedidoOrdenadosPorRecebimentoEStatus
      listagemDePedidoOrdenadosPorRecebimentoEStatus() {
    return new ListagemDePedidoOrdenadosPorRecebimentoEStatus(pedidoRepository);
  }

  @Bean
  public ListagemDePedidoPorStatus listagemDePedidoPorStatus() {
    return new ListagemDePedidoPorStatus(pedidoRepository);
  }
}
