package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.StatusPagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.PedidoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class InformacoesDePagamentoDoPedidoTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private InformacoesDePagamentoDoPedido informacoesDePagamentoDoPedido;

    @Test
    public void devePermitirObterInformacoesSobreStatusDePagamentoDoPedido(){

        //Arrange
        Pedido pedido = PedidoHelper.criarPedidoCompleto();

        Mockito.when(pedidoRepository.listarPedidoPorNumeroPedido(pedido.getNumeroPedido())).thenReturn(pedido);

        //Act
        StatusPagamento status = informacoesDePagamentoDoPedido.verificaStatusPagamentoPedido(pedido.getNumeroPedido());

        Assertions.assertThat(status).isInstanceOf(StatusPagamento.class);
        Assertions.assertThat(status).isEqualTo(pedido.getPagamento().getStatusPagamento());
    }
}