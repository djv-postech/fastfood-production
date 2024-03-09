package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido;

import com.fiap.postech.techchallenge.fastfoodproduction.application.records.*;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.cliente.Cliente;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.Pagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.StatusPagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.TipoPagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.StatusPedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.vo.CPF;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.vo.Email;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PedidoHelper {


    public static Pedido criarPedidoCompleto() {
        return new Pedido("numeroPedido", cliente(), listaProdutos(), BigDecimal.TEN, pagamento(), StatusPedido.RECEBIDO, LocalDateTime.now());
    }

    private static Pagamento pagamento() {
        return new Pagamento(LocalDateTime.now(), StatusPagamento.APROVADO, TipoPagamento.CARTAO, BigDecimal.ONE);
    }

    private static List<Produto> listaProdutos() {
        return Collections.singletonList(gerarProduto());
    }

    private static Cliente cliente() {
        return new Cliente("Jackson", new CPF("222.222.222-22"), new Email("jackson@gmail.com"));
    }

    public static Produto gerarProduto() {
        return new Produto("Big Mac", "Descricao Big Mac", Categoria.LANCHE);
    }

    public static Pedido criarPedidoCom(StatusPedido statusPedido) {
        return new Pedido("numeroPedido", cliente(), listaProdutos(), new BigDecimal("20"), pagamento(), statusPedido, LocalDateTime.now());
    }

    public static DadosCadastroPedido criarDadosCadastroPedido() {
        return new DadosCadastroPedido(dadosCadastroCliente(), dadosCadastroProduto(), BigDecimal.TEN, dadosCadastroPagamento(), StatusPedido.PRONTO,
                LocalDateTime.now());
    }

    private static DadosCadastroPagamento dadosCadastroPagamento() {
        return new DadosCadastroPagamento(TipoPagamento.CARTAO, BigDecimal.TEN, LocalDateTime.now(), StatusPagamento.APROVADO);
    }

    public static DadosStatusPagamento dadosStatusPagamento() {
        return new DadosStatusPagamento("123456", StatusPagamento.APROVADO);
    }

    public static DadosNotificacao dadosNotificacao() {
        return new DadosNotificacao("Cliente", "email@email.com", "Seu pedido está pronto!");
    }

    private static List<DadosCadastroProduto> dadosCadastroProduto() {
        DadosCadastroProduto dadosCadastroProduto = new DadosCadastroProduto(
                "Teste", "Descricao", BigDecimal.TEN, 10, Categoria.LANCHE);

        DadosCadastroProduto dadosCadastroProduto1 = new DadosCadastroProduto(
                "Teste2", "Descricao2", BigDecimal.ONE, 10, Categoria.BEBIDA);


        return Arrays.asList(dadosCadastroProduto1, dadosCadastroProduto);
    }

    private static DadosCadastroCliente dadosCadastroCliente() {
        return new DadosCadastroCliente("Teste", "222.222.222-22", "teste@gmail.com");
    }

    public static DadosPedido criarDadosPedido(){
     return new DadosPedido("1", List.of(new DadosProduto("1", "BigMac", "Descricao Big", BigDecimal.TEN, Categoria.LANCHE, 1)),
                new DadosCliente("Test", new CPF("333.333.333-33"), new Email("teste@gmail.com")),
                        new DadosPagamento("1", BigDecimal.TEN, TipoPagamento.CARTAO, LocalDateTime.now(), StatusPagamento.APROVADO),
             StatusPedido.PRONTO, LocalDateTime.now(), BigDecimal.TEN, "");
    }

    public static DadosPedido criarDadosPedidoSemClienteIdentificado(){
        return new DadosPedido("1", List.of(new DadosProduto("1", "BigMac", "Descricao Big", BigDecimal.TEN, Categoria.LANCHE, 1)),
                null,
                new DadosPagamento("1", BigDecimal.TEN, TipoPagamento.CARTAO, LocalDateTime.now(), StatusPagamento.APROVADO),
                StatusPedido.PRONTO, LocalDateTime.now(), BigDecimal.TEN, "");
    }
}

