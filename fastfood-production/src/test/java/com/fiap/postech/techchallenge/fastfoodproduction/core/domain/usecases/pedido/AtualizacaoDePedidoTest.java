package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido;

import com.fiap.postech.techchallenge.fastfoodproduction.application.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.StatusPagamento;
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

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtualizacaoDePedidoTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private NotificacaoService notificacaoService;

    @InjectMocks
    private AtualizacaoDePedido atualizacaoDePedido;


    @Test
    public void devePermitirAtualizarStatusDoPedido(){
        // Dado
        Pedido pedido = PedidoHelper.criarPedidoCompleto();
        pedido.setNumeroPedido("1");

        when(pedidoRepository.listarPedidoPorNumeroPedido(pedido.getNumeroPedido()))
                .thenReturn(pedido);

        when(pedidoRepository.atualizarPedido(pedido)).thenAnswer(answer -> answer.getArgument(0));

        // Quando
        Pedido pedidoAtualizado = atualizacaoDePedido.atualizarStatusPedido(pedido.getNumeroPedido(), StatusPedido.EM_PREPARACAO);

        // Entao
        Assertions.assertThat(pedidoAtualizado).isInstanceOf(Pedido.class);
        Assertions.assertThat(pedidoAtualizado.getStatusPedido()).isEqualTo(StatusPedido.EM_PREPARACAO);

    }

    @Test
    public void devePermitirAtualizarStatusDoPedidoQuandoClienteNaoEstaIdentificado(){
        // Dado
        Pedido pedido = PedidoHelper.criarDadosPedidoSemClienteIdentificado().convertToPedido();
        pedido.setNumeroPedido("1");

        when(pedidoRepository.listarPedidoPorNumeroPedido(pedido.getNumeroPedido()))
                .thenReturn(pedido);

        when(pedidoRepository.atualizarPedido(pedido)).thenAnswer(answer -> answer.getArgument(0));

        // Quando
        Pedido pedidoAtualizado = atualizacaoDePedido.atualizarStatusPedido(pedido.getNumeroPedido(), StatusPedido.EM_PREPARACAO);

        // Entao
        Assertions.assertThat(pedidoAtualizado).isInstanceOf(Pedido.class);
        Assertions.assertThat(pedidoAtualizado.getStatusPedido()).isEqualTo(StatusPedido.EM_PREPARACAO);

    }

    @Test
    public void devePermitirAtualizarStatusDoPagamentoDoPedido(){
        // Dado
        Pedido pedido = PedidoHelper.criarPedidoCompleto();
        pedido.setNumeroPedido("1");

        when(pedidoRepository.listarPedidoPorNumeroPedido(pedido.getNumeroPedido()))
                .thenReturn(pedido);

        when(pedidoRepository.atualizarPedido(pedido)).thenAnswer(answer -> answer.getArgument(0));

        // Quando
        Pedido pedidoAtualizado = atualizacaoDePedido.atualizarStatusPagamentoPedido(pedido.getNumeroPedido(), StatusPagamento.CANCELADO);

        // Entao
        Assertions.assertThat(pedidoAtualizado).isInstanceOf(Pedido.class);
        Assertions.assertThat(pedidoAtualizado.getPagamento().getStatusPagamento()).isEqualTo(StatusPagamento.CANCELADO);

    }

    @Test
    public void devePermitirAtualizarStatusDoPagamentoDoPedidoQuandoClienteNaoEstaIdentificado(){
        // Dado
        Pedido pedido = PedidoHelper.criarDadosPedidoSemClienteIdentificado().convertToPedido();
        pedido.setNumeroPedido("1");

        when(pedidoRepository.listarPedidoPorNumeroPedido(pedido.getNumeroPedido()))
                .thenReturn(pedido);

        when(pedidoRepository.atualizarPedido(pedido)).thenAnswer(answer -> answer.getArgument(0));

        // Quando
        Pedido pedidoAtualizado = atualizacaoDePedido.atualizarStatusPagamentoPedido(pedido.getNumeroPedido(), StatusPagamento.CANCELADO);

        // Entao
        Assertions.assertThat(pedidoAtualizado).isInstanceOf(Pedido.class);
        Assertions.assertThat(pedidoAtualizado.getPagamento().getStatusPagamento()).isEqualTo(StatusPagamento.CANCELADO);
    }

}