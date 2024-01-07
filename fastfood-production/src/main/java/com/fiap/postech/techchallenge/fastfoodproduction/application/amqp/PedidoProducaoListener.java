package com.fiap.postech.techchallenge.fastfoodproduction.application.amqp;

import com.fiap.postech.techchallenge.fastfoodproduction.application.records.DadosCadastroPedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido.CadastroDePedido;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.fiap.postech.techchallenge.fastfoodproduction.infra.config.amqp.ProducaoAMQPConfiguration.PEDIDO_PRODUCAO_COZINHA_QUEUE;

@Component
public class PedidoProducaoListener {

    private final CadastroDePedido cadastroDePedido;

    public PedidoProducaoListener(CadastroDePedido cadastroDePedido) {
        this.cadastroDePedido = cadastroDePedido;
    }

    @RabbitListener(queues = PEDIDO_PRODUCAO_COZINHA_QUEUE)
    public void cadastrarPedido(DadosCadastroPedido dadosCadastroPedido){
          cadastroDePedido.cadastrarPedido(dadosCadastroPedido.toPedido());
    }
}
