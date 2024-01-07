package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.produto;

import java.util.List;

public interface ProdutoRepository {

    Produto cadastrarProduto(Produto produto);

    Produto listarProdutoPorId(String id);

    Produto atualizarProduto(Produto produto);

    void removerProduto(String id);

    List<Produto> listarProdutos();

    List<Produto> listaProdutosPorCategoria(Categoria categoria);

    Produto listarProdutoPorNome(String nome);
}
