package com.fiap.postech.techchallenge.fastfoodproduction.infra.persistence.repository.converter;

import com.fiap.postech.techchallenge.fastfoodproduction.application.records.DadosNotificacao;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.cliente.Cliente;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.Pagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.StatusPedido;

import java.util.Objects;


public class NotificacaoConverter {

    public static DadosNotificacao criarNotificacao(String numeroPedido, Cliente cliente, StatusPedido statusPedido,
                                                    Pagamento pagamento) {

        DadosNotificacao dadosNotificacao = new DadosNotificacao();
        dadosNotificacao.setNumeroPedido(numeroPedido);
        dadosNotificacao.setStatusPedido(statusPedido);

        if(Objects.nonNull(cliente)){
            dadosNotificacao.setNome(cliente.getNome());
            dadosNotificacao.setEmail(cliente.getEmail().getEndereco());
        }

        if(Objects.nonNull(pagamento)){
            dadosNotificacao.setStatusPagamento(pagamento.getStatusPagamento());
        }

        return dadosNotificacao;
    }
}
