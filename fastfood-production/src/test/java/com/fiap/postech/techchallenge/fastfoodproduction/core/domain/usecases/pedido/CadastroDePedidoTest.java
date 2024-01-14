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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastroDePedidoTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private CadastroDePedido cadastroDePedido;

    @Test
    public void devePermitirCadastrarPedido(){
        //Arrange
        Pedido pedido = PedidoHelper.criarPedidoCompleto();

        when(pedidoRepository.cadastrarPedido(Mockito.any(Pedido.class)))
                .thenAnswer(answer -> answer.getArgument(0));

        //Act
        Pedido pedidoCadastrado = cadastroDePedido.cadastrarPedido(pedido);

        //Assert
        Assertions.assertThat(pedidoCadastrado).isInstanceOf(Pedido.class);
        Assertions.assertThat(pedidoCadastrado).isEqualTo(pedido);


    }

}