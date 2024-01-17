package com.fiap.postech.techchallenge.fastfoodproduction.application.amqp;

import com.fiap.postech.techchallenge.fastfoodproduction.application.records.DadosCliente;
import com.fiap.postech.techchallenge.fastfoodproduction.application.records.DadosPagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.application.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodproduction.application.records.DadosProduto;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.StatusPedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido.CadastroDePedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido.PedidoHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class PedidoProducaoListenerTest {

    @Mock
    private  CadastroDePedido cadastroDePedido;

    @InjectMocks
    private PedidoProducaoListener pedidoProducaoListener;

    @Test
    public void devePermitirReceberDadosDoPedidoDaFilaEEfetuarOCadastro(){
        DadosPedido dadosPedido = PedidoHelper.criarDadosPedido();

        Pedido pedido = dadosPedido.convertToPedido();

        Mockito.when(cadastroDePedido.cadastrarPedido(any(Pedido.class))).thenReturn(pedido);

        pedidoProducaoListener.cadastrarPedido(dadosPedido);

        Mockito.verify(cadastroDePedido, Mockito.times(1)).cadastrarPedido(any());
    }


}