package com.fiap.postech.techchallenge.fastfoodproduction.application.records;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.cliente.Cliente;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.Pagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.StatusPedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.vo.CPF;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.vo.Email;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DadosCadastroPedido(

    DadosCadastroCliente cliente,

    @NotNull
    List<DadosCadastroProduto> produtos,

    @NotNull
    BigDecimal valorTotal,

    @NotNull
    DadosCadastroPagamento pagamento,

    @NotNull
    StatusPedido statusPedido,


    @NotNull @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime dataCriacaoPedido) {

}
