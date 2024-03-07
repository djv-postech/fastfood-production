package com.fiap.postech.techchallenge.fastfoodproduction.application.amqp;

import com.fiap.postech.techchallenge.fastfoodproduction.application.records.DadosStatusPedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido.AtualizacaoDePedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido.PedidoHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AtualizacaoStatusDePagamentoListenerTest {

    @Mock
    private AtualizacaoDePedido atualizacaoDePedido;

    @InjectMocks
    private AtualizacaoStatusDePedidoListener atualizacaoStatusDePedidoListener;

    @Test
    public void deveAtualizarStatusDePedidoAoReceberAtualizacaoDePagamento() {
        // Dado
        DadosStatusPedido dadosStatusPedido = PedidoHelper.dadosStatusPedido();

        // Quando
        atualizacaoStatusDePedidoListener.atualizarStatusPedido(dadosStatusPedido);

        // Então
        Mockito.verify(atualizacaoDePedido, Mockito.times(1)).atualizarStatusPedido(any(), any());
    }


}