package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido;

import com.fiap.postech.techchallenge.fastfoodproduction.application.records.DadosNotificacao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static com.fiap.postech.techchallenge.fastfoodproduction.infra.config.amqp.ProducaoAMQPConfiguration.NOTIFICACAO_EX;

@Slf4j
public class NotificacaoService {

    private RabbitTemplate rabbitTemplate;

    public NotificacaoService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarParaFilaDeNotificacao(DadosNotificacao dadosNotificacao) {
        rabbitTemplate.convertAndSend(NOTIFICACAO_EX, "", dadosNotificacao);

        log.info("Notificação enviada! Payload: {}", dadosNotificacao);

    }
}
