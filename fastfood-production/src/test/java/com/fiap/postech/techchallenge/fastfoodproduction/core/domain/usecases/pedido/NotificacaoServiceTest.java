package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido;

import com.fiap.postech.techchallenge.fastfoodproduction.application.records.DadosNotificacao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class NotificacaoServiceTest {


    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private NotificacaoService notificacaoService;

    @Test
    public void deveEnviarMensagemDeNotificacaoParaFila() {
        // Dado
        DadosNotificacao dadosNotificacao = PedidoHelper.dadosNotificacao();

        // Quando
        notificacaoService.enviarParaFilaDeNotificacao(dadosNotificacao);

        // Então
        Mockito.verify(rabbitTemplate, Mockito.times(1)).convertAndSend(anyString(), anyString(), any(DadosNotificacao.class));
    }
}