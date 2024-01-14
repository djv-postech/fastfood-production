package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExclusaoDePedidoTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private ExclusaoDePedido exclusaoDePedido;

    @Test
    public void devePermitirExluirPedido(){
        //Arrange
        doNothing().when(pedidoRepository).excluirPedido(anyString());

        //Act
        exclusaoDePedido.excluirPedido("1");

        //Assert
        verify(pedidoRepository, times(1)).excluirPedido(anyString());
    }


}