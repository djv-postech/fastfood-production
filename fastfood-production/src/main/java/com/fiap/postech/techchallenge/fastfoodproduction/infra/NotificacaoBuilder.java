package com.fiap.postech.techchallenge.fastfoodproduction.infra;

import com.fiap.postech.techchallenge.fastfoodproduction.application.records.DadosNotificacao;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.cliente.Cliente;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.notificacao.Notificacao;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.Pagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.StatusPagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.StatusPedido;

import java.util.Objects;


public class NotificacaoBuilder {

    public static DadosNotificacao criarNotificacaoStatusPedido(String numeroPedido, Cliente cliente, String statusPedido) {

        if (Objects.isNull(cliente)) {
            return new DadosNotificacao(new Notificacao(textoStatusPedidoSemIdentificacao(numeroPedido, statusPedido)));
        }

        return new DadosNotificacao(cliente.getNome(),
                cliente.getEmail().getEndereco(), textoStatusPedido(cliente.getNome(), numeroPedido, statusPedido));

    }

    private static String textoStatusPedidoSemIdentificacao(String numeroPedido, String statusPedido) {
        return "Pedido de número " + numeroPedido + getTextoStatus(statusPedido);
    }

    private static String textoStatusPedido(String nome, String numeroPedido, String statusPedido) {
        return nome +  " seu pedido de número " + numeroPedido + getTextoStatus(statusPedido);
    }

    private static String getTextoStatus(String statusPedido) {

        var textoStatus = StatusPedido.EM_PREPARACAO.getValue().equals(statusPedido) ||
                StatusPedido.PRONTO.getValue().equals(statusPedido) ? " está " : " foi ";

        return textoStatus + statusPedido;
    }

    public static DadosNotificacao criarNotificacaoStatusPagamento(String numeroPedido, Cliente cliente,
                                                                   Pagamento pagamento) {

        if(Objects.isNull(cliente)){
            return new DadosNotificacao(new Notificacao(textoStatusPagamentoSemIdentificacao(
                    numeroPedido, pagamento.getStatusPagamento())));
        }

        return new DadosNotificacao(cliente.getNome(), cliente.getEmail().getEndereco(),
                textoStatusPagamentoComIdentificacao(cliente.getNome(), numeroPedido, pagamento.getStatusPagamento()));

    }

    private static String textoStatusPagamentoComIdentificacao(String nome, String numeroPedido, StatusPagamento statusPagamento) {
        return nome + " o seu pedido de número " + numeroPedido + textoStatusPagamento(statusPagamento);
    }

    private static String textoStatusPagamentoSemIdentificacao(String numeroPedido, StatusPagamento statusPagamento) {
        return "O pedido de número " + numeroPedido + textoStatusPagamento(statusPagamento);
    }

    private static String textoStatusPagamento(StatusPagamento statusPagamento) {
        var textoStatusPagamento =
                StatusPagamento.PROCESSANDO.equals(statusPagamento) ? " está " : " foi ";

        return  textoStatusPagamento + statusPagamento;
    }
}