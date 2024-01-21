package com.fiap.postech.techchallenge.fastfoodproduction.application.records;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.produto.Produto;

import java.math.BigDecimal;

public record DadosProduto(String id, String nome, String descricao, BigDecimal preco, Categoria categoria,
                           Integer quantidade) {

    public DadosProduto(Produto dadosProduto) {
        this(dadosProduto.getId(), dadosProduto.getNome(), dadosProduto.getDescricao(),
                dadosProduto.getPreco(), dadosProduto.getCategoria(), dadosProduto.getQuantidade());
    }
}
