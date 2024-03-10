package com.fiap.postech.techchallenge.fastfoodproduction.application.amqp;

import com.fiap.postech.techchallenge.fastfoodproduction.application.records.DadosStatusPagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido.AtualizacaoDePedido;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.fiap.postech.techchallenge.fastfoodproduction.infra.config.amqp.ProducaoAMQPConfiguration.STATUS_PAGAMENTO;


@Slf4j
@Component
public class AtualizacaoStatusDePagamentoListener {

    private final AtualizacaoDePedido atualizacaoDePedido;

    public AtualizacaoStatusDePagamentoListener(AtualizacaoDePedido atualizacaoDePedido) {
        this.atualizacaoDePedido = atualizacaoDePedido;
    }

    @RabbitListener(queues = STATUS_PAGAMENTO)
    public void atualizarStatusDePagamento(DadosStatusPagamento dadosStatusPagamento) {
        log.info("Atualização de pagamento recebida! Payload: {}", dadosStatusPagamento);
        atualizacaoDePedido.atualizarStatusPagamentoPedido(dadosStatusPagamento.numeroPedido(), dadosStatusPagamento.statusPagamento());

    }
}

