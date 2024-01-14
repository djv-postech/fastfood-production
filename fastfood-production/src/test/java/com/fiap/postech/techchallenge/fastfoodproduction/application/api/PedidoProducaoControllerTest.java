package com.fiap.postech.techchallenge.fastfoodproduction.application.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.postech.techchallenge.fastfoodproduction.application.records.DadosCadastroPedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.StatusPedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.fiap.postech.techchallenge.fastfoodproduction.infra.config.amqp.ProducaoAMQPConfiguration.PEDIDO_PRODUCAO_EX;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class PedidoProducaoControllerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private ListagemDePedidoPorNumeroDePedido listarPedidoPorNumeroPedido;

    @Mock
    private ListagemDePedidoPorStatus listarPedidoPorStatus;

    @Mock
    private ListagemDePedidoOrdenadosPorRecebimentoEStatus listagemDePedidoOrdenadosPorRecebimentoEStatus;

    @Mock
    private AtualizacaoDePedido atualizacaoDePedido;

    private MockMvc mockMvc;

    private PedidoProducaoController pedidoProducaoController;

    @BeforeEach
    public void init() {
        pedidoProducaoController = new PedidoProducaoController(listarPedidoPorNumeroPedido, listarPedidoPorStatus,
                listagemDePedidoOrdenadosPorRecebimentoEStatus, atualizacaoDePedido, rabbitTemplate);

        this.mockMvc = MockMvcBuilders.standaloneSetup(pedidoProducaoController).build();
    }

    @DisplayName("Test - Deve permitir enviar pedido para fila de produção")
    @Test
    public void devePermitirEnviarPedidoParaFilaDeProducao() throws Exception {
        // Dado
        DadosCadastroPedido dadosCadastroPedido = PedidoHelper.criarDadosCadastroPedido();

        doNothing().when(rabbitTemplate).convertAndSend(PEDIDO_PRODUCAO_EX, "", dadosCadastroPedido);

        // Quando
        mockMvc.perform(MockMvcRequestBuilders.post("/producao/pedido")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(dadosCadastroPedido)))
                .andExpect(status().isOk());
    }

    @DisplayName("Test - Deve permitir listar pedido por número de pedido")
    @Test
    public void devePermitirBuscarPedidoPorNumeroPedido() throws Exception {
        // Dado
        String id = "1";
        Pedido pedido = PedidoHelper.criarPedidoCompleto();

        given(listarPedidoPorNumeroPedido.listarPedidoPorNumeroPedido(id))
                .willReturn(pedido);

        // Quando
        mockMvc.perform(MockMvcRequestBuilders.get("/producao/pedido/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("Test - Deve permitir listar pedidos por status")
    @Test
    public void devePermitirBuscarPedidoPorStatusDoPedido() throws Exception {
        // Dado
        Pedido pedido = PedidoHelper.criarPedidoCompleto();
        List<Pedido> pedidos = Arrays.asList(pedido);

        given(listarPedidoPorStatus.listarPedidoPorStatus(StatusPedido.RECEBIDO))
                .willReturn(pedidos);

        // Quando
        mockMvc.perform(MockMvcRequestBuilders.get("/producao/pedido/status/RECEBIDO")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String convertToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }
}