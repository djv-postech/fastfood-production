package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.PedidoRepository;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.StatusPedido;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListagemDePedidoOrdenadosPorRecebimentoEStatusTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private ListagemDePedidoOrdenadosPorRecebimentoEStatus listagemDePedidoOrdenadosPorRecebimentoEStatus;


    @Test
    public void devePermitirListaPedidosOrdenadosPorRecebimentoEStatus(){
        //Arrange
        Pedido pedido2 = PedidoHelper.criarPedidoCom(StatusPedido.RECEBIDO);
        Pedido pedido3 = PedidoHelper.criarPedidoCom(StatusPedido.EM_PREPARACAO);

        List<Pedido> listaDePedidosPorStatus = Arrays.asList(pedido2, pedido3);

        when(pedidoRepository.listarPedidos()).thenReturn(listaDePedidosPorStatus);

        //Act
        List<Pedido> pedidos = listagemDePedidoOrdenadosPorRecebimentoEStatus.listarPedidosOrdenadosPorRecebimentoEStatus();

        //Assert
        Assertions.assertThat(pedidos).isNotNull();
        Assertions.assertThat(pedidos.size()).isEqualTo(listaDePedidosPorStatus.size());

    }
}