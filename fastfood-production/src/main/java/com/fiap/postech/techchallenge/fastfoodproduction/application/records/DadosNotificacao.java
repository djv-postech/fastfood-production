package com.fiap.postech.techchallenge.fastfoodproduction.application.records;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.StatusPagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.StatusPedido;
import lombok.Getter;


public class DadosNotificacao {

    private String numeroPedido;
    private String nome;
    private String email;
    private StatusPedido statusPedido;
    private StatusPagamento statusPagamento;

    public DadosNotificacao(String numeroPedido, String nome, String email, StatusPedido statusPedido, StatusPagamento statusPagamento) {
        this.numeroPedido = numeroPedido;
        this.nome = nome;
        this.email = email;
        this.statusPedido = statusPedido;
        this.statusPagamento = statusPagamento;
    }

    public DadosNotificacao(){

    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }
}
