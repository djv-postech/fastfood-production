package com.fiap.postech.techchallenge.fastfoodproduction.application.config.beans;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido.NotificacaoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificacaoBeanConfiguration {

    private RabbitTemplate rabbitTemplate;

    public NotificacaoBeanConfiguration(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Bean
    public NotificacaoService notificacao() {
        return new NotificacaoService(rabbitTemplate);
    }
}
