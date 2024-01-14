package com.fiap.postech.techchallenge.fastfoodproduction.infra.persistence.repository;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.StatusPedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido.PedidoHelper;
import com.fiap.postech.techchallenge.fastfoodproduction.infra.persistence.exception.NotFoundException;
import com.fiap.postech.techchallenge.fastfoodproduction.infra.persistence.repository.converter.PedidoConverter;
import com.fiap.postech.techchallenge.fastfoodproduction.infra.persistence.repository.entity.PedidoEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


@DataMongoTest
class PedidoRepositoryImplTest extends  AbstractionBaseTest{

    @Autowired
    private PedidoRepositoryMongo pedidoRepositoryMongo;

    private PedidoConverter pedidoConverter;

    private PedidoRepositoryImpl pedidoRepositoryImpl;

    @BeforeEach
    public void config(){
        pedidoConverter = new PedidoConverter();
        pedidoRepositoryImpl = new PedidoRepositoryImpl(pedidoRepositoryMongo, pedidoConverter);
    }

    @AfterEach
    void cleanUp() {
        this.pedidoRepositoryMongo.deleteAll();
    }
    
    @Test
    public void devePermitirCadastrarPedidoNoMongoDB(){
        Pedido pedido = PedidoHelper.criarPedidoCompleto();

        Pedido pedidoCadastrado = pedidoRepositoryImpl.cadastrarPedido(pedido);

        Assertions.assertThat(pedidoCadastrado).isNotNull().isInstanceOf(Pedido.class);
        Assertions.assertThat(pedidoCadastrado.getNumeroPedido()).isNotNull();
    }

    @Test
    public void devePermitirAtualizarPedidoNoMongoDB(){
        Pedido pedido = PedidoHelper.criarPedidoCompleto();

        Pedido pedidoCadastrado = pedidoRepositoryImpl.atualizarPedido(pedido);

        Assertions.assertThat(pedidoCadastrado).isNotNull().isInstanceOf(Pedido.class);
        Assertions.assertThat(pedidoCadastrado.getNumeroPedido()).isNotNull();
    }

    @Test
    public void devePermitirBuscarPedidoPorNumeroPedidoNoMongoDB(){
        Pedido pedido = PedidoHelper.criarPedidoCompleto();

        PedidoEntity entity = pedidoRepositoryMongo.insert(pedidoConverter.convertToEntity(pedido));

        Pedido pedidoCadastrado = pedidoRepositoryImpl.listarPedidoPorNumeroPedido(entity.getNumeroPedido());

        Assertions.assertThat(pedidoCadastrado).isNotNull().isInstanceOf(Pedido.class);
        Assertions.assertThat(pedidoCadastrado.getNumeroPedido()).isNotNull().isEqualTo(entity.getNumeroPedido());
    }

    @Test
    public void deveLancarExcecaoAoBuscarPorPedidoInexistenteNoMongoDB(){
        String id = UUID.randomUUID().toString();
       Assertions.assertThatThrownBy(() -> pedidoRepositoryImpl.listarPedidoPorNumeroPedido(id))
               .isInstanceOf(NotFoundException.class)
               .hasMessageContaining("Pedido de Id: "+ id +" não encontrado");

    }

    @Test
    public void devePermitirBuscarTodosOsPedidoNoMongoDB(){
        Pedido pedido = PedidoHelper.criarPedidoCompleto();

        pedidoRepositoryMongo.insert(pedidoConverter.convertToEntity(pedido));

        List<Pedido> pedidos = pedidoRepositoryImpl.listarPedidos();

        Assertions.assertThat(pedidos).hasOnlyElementsOfType(Pedido.class).hasSize(1);

    }

    @Test
    public void devePermitirBuscarPedidosPorStatusDoPedidoNoMongoDB(){
        Pedido pedido = PedidoHelper.criarPedidoCom(StatusPedido.PRONTO);

        pedidoRepositoryMongo.insert(pedidoConverter.convertToEntity(pedido));

        List<Pedido> pedidos = pedidoRepositoryImpl.listarPedidosPorStatus(StatusPedido.PRONTO);

        Assertions.assertThat(pedidos).hasOnlyElementsOfType(Pedido.class).hasSize(1);
        Assertions.assertThat(Objects.requireNonNull(pedidos.stream()
                .findFirst()
                .orElse(null))
                .getStatusPedido()).isEqualTo(StatusPedido.PRONTO);

    }

    @Test
    public void devePermitirExcluirPedidoNoMongoDB(){
        Pedido pedido = PedidoHelper.criarPedidoCompleto();

        PedidoEntity entity = pedidoRepositoryMongo.insert(pedidoConverter.convertToEntity(pedido));

        pedidoRepositoryImpl.excluirPedido(entity.getNumeroPedido());

        Assertions.assertThat(pedidoRepositoryMongo.findById(entity.getNumeroPedido())).isEmpty();
    }
}