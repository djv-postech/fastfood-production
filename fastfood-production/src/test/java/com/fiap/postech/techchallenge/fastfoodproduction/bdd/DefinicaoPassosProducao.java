package com.fiap.postech.techchallenge.fastfoodproduction.bdd;

import com.fiap.postech.techchallenge.fastfoodproduction.application.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.StatusPagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.StatusPedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido.PedidoHelper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class DefinicaoPassosProducao{

    private DadosPedido dadosPedido;
    private Response response;


    private final String BASE_URL = "http://localhost:8082";
    private final String ENDPOINT_API_PRODUCAO = BASE_URL + "/producao/pedido";


    @Quando("cadastrar um novo pedido")
    public DadosPedido cadastrar_um_novo_pedido() {
        DadosPedido dadosPedido = PedidoHelper.criarDadosPedido();

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(dadosPedido)
                .when()
                .post(ENDPOINT_API_PRODUCAO + "/cadastro");

        return response.then().extract().as(DadosPedido.class);
    }

    @Entao("o pedido é cadastrado")
    public void o_pedido_é_cadastrado() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Entao("deve ser retornado com sucesso")
    public void deve_ser_retornado() {
        response.then()
                .body(matchesJsonSchemaInClasspath("schemas/dados_pedido.schema.json"));
    }

    @Dado("que um pedido foi cadastrado")
    public void que_um_pedido_foi_cadastrado() {
        dadosPedido = cadastrar_um_novo_pedido();
    }
    @Quando("quando buscar pelo numero do pedido")
    public void quando_buscar_pelo_numero_do_pedido() {
        response = given()
                .when()
                .get(ENDPOINT_API_PRODUCAO + "/{numeroPedido}", dadosPedido.id());
    }

    @Quando("quando buscar pelo numero do pedido que nao foi cadastrado")
    public void quando_buscar_pelo_numero_do_pedido_que_nao_foi_cadastrado() {
        response = given()
                .when()
                .get(ENDPOINT_API_PRODUCAO + "/{numeroPedido}", "100");
    }
    @Entao("uma mensagem de erro deve ser apresentada")
    public void uma_mensagem_de_erro_deve_ser_apresentada() {
        response.then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(matchesJsonSchemaInClasspath("schemas/erro_pedido_not_found.schema.json"));
    }

    @Quando("buscar pedidos pelo status do pedido")
    public void buscar_pedidos_pelo_status_do_pedido() {
        response = given()
                .when()
                .get(ENDPOINT_API_PRODUCAO + "/status/{status}", dadosPedido.status());
    }
    @Entao("a lista de pedidos é exibida com sucesso")
    public void a_lista_de_pedidos_é_exibida_com_sucesso() {
        response.then()
                .body(matchesJsonSchemaInClasspath("schemas/lista_pedido.schema.json"));
    }

    @Quando("buscar pedidos ordenados por data de recebimento e status")
    public void buscar_pedidos_ordenados_por_data_de_recebimento_e_status() {
        response = given()
                .when()
                .get(ENDPOINT_API_PRODUCAO + "/todos");
    }

    @Quando("efetuar alteração do status do pedido")
    public void efetuar_alteração_do_status_do_pedido() {
        response = given()
                .when()
                .put(ENDPOINT_API_PRODUCAO + "/{numeroPedido}/statusPedido/{status}", dadosPedido.id(), StatusPedido.RECEBIDO);
    }
    @Entao("o status do pedido é alterado com sucesso")
    public void o_status_do_pedido_é_alterado_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }
    @Entao("o pedido é retornado")
    public void o_pedido_é_retornado() {
        response.then()
                .body(matchesJsonSchemaInClasspath("schemas/dados_pedido.schema.json"));
    }

    @Quando("efetuar alteração do status de pagamento do pedido")
    public void efetuar_alteração_do_status_de_pagamento_do_pedido() {
        response = given()
                .when()
                .put(ENDPOINT_API_PRODUCAO + "/{numeroPedido}/statusPagamento/{statusPagamento}", dadosPedido.id(), StatusPagamento.APROVADO);
    }
    @Entao("o status de pagamento do pedido é alterado com sucesso")
    public void o_status_de_pagamento_do_pedido_é_alterado_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }


}
