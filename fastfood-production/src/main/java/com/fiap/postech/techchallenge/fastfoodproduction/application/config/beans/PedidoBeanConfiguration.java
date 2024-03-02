package com.fiap.postech.techchallenge.fastfoodproduction.application.config.beans;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.PedidoRepository;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoBeanConfiguration {

  private final PedidoRepository pedidoRepository;
  private final RabbitTemplate rabbitTemplate;
  private final NotificacaoService notificacaoService;


  public PedidoBeanConfiguration(PedidoRepository pedidoRepository, RabbitTemplate rabbitTemplate, NotificacaoService notificacaoService) {
    this.pedidoRepository = pedidoRepository;
    this.rabbitTemplate = rabbitTemplate;
    this.notificacaoService = notificacaoService;
  }

  @Bean
  public CadastroDePedido cadastroDePedido() {
    return new CadastroDePedido(pedidoRepository);
  }

  @Bean
  public AtualizacaoDePedido atualizacaoDePedido() {
    return new AtualizacaoDePedido(pedidoRepository, notificacaoService);
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
