package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.PedidoRepository;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.StatusPedido;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ListagemDePedidoPorStatusTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private ListagemDePedidoPorStatus listagemDePedidoPorStatus;

    @Test
    public void devePermitirListarPedidosPorStatusPedido(){

        //Arrange
        Pedido pedido = PedidoHelper.criarPedidoCompleto();

        Mockito.when(pedidoRepository.listarPedidosPorStatus(pedido.getStatusPedido()))
                .thenReturn(Collections.singletonList(pedido));

        //Act
        List<Pedido> pedidosPorStatus = listagemDePedidoPorStatus.listarPedidoPorStatus(StatusPedido.RECEBIDO);

        //Assert
        Assertions.assertThat(pedidosPorStatus).isNotNull();
        Assertions.assertThat(Objects.requireNonNull(pedidosPorStatus.stream()
                .findFirst()
                .orElse(null))
                .getStatusPedido())
        .isEqualTo(pedido.getStatusPedido());
    }

}