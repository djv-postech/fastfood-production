package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.PedidoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ListagemDePedidoPorNumeroDePedidoTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private ListagemDePedidoPorNumeroDePedido listagemDePedidoPorNumeroDePedido;

    @Test
    public void devePermitirListarPedidoPorNumeroDePedido(){

        //Arrange
        Pedido pedido = PedidoHelper.criarPedidoCompleto();

        Mockito.when(pedidoRepository.listarPedidoPorNumeroPedido(pedido.getNumeroPedido())).thenReturn(pedido);

        //Act
        Pedido pedidoBanco = listagemDePedidoPorNumeroDePedido
                .listarPedidoPorNumeroPedido(pedido.getNumeroPedido());

        //Assert
        Assertions.assertThat(pedidoBanco).isInstanceOf(Pedido.class);
        Assertions.assertThat(pedidoBanco).isEqualTo(pedido);
    }

}