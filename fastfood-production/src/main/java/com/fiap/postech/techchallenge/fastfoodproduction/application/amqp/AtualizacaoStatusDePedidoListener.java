package com.fiap.postech.techchallenge.fastfoodproduction.application.amqp;

import com.fiap.postech.techchallenge.fastfoodproduction.application.records.DadosStatusPedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido.AtualizacaoDePedido;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.fiap.postech.techchallenge.fastfoodproduction.infra.config.amqp.ProducaoAMQPConfiguration.STATUS_PEDIDO;


@Slf4j
@Component
public class AtualizacaoStatusDePedidoListener {

    //FIXME: Provavelmente nao precisemos desse listener, temos o endpoint que ja chama o usecase de atualizaçao de pedido, não faria muito sentido o endpoint chamar uma use case, que posta via fila para a fila chamar a use case de atualizacao.
    private final AtualizacaoDePedido atualizacaoDePedido;

    public AtualizacaoStatusDePedidoListener(AtualizacaoDePedido atualizacaoDePedido) {
        this.atualizacaoDePedido = atualizacaoDePedido;
    }

    @RabbitListener(queues = STATUS_PEDIDO)
    public void atualizarStatusPedido(DadosStatusPedido dadosStatusPedido) {
        log.info("Atualização de status de pedido recebida. Payload: {}", dadosStatusPedido);
        atualizacaoDePedido.atualizarStatusPedido(dadosStatusPedido.numeroPedido(), dadosStatusPedido.statusPedido());
    }
}

