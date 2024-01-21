package com.fiap.postech.techchallenge.fastfoodproduction.application.api;

import com.fiap.postech.techchallenge.fastfoodproduction.application.records.DadosCadastroPedido;
import com.fiap.postech.techchallenge.fastfoodproduction.application.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodproduction.application.records.DadosProduto;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.StatusPagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.StatusPedido;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.usecases.pedido.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.fiap.postech.techchallenge.fastfoodproduction.infra.config.amqp.ProducaoAMQPConfiguration.PEDIDO_PRODUCAO_EX;

@RestController
@RequestMapping("/producao")
public class PedidoProducaoController {

    private final RabbitTemplate rabbitTemplate;
    private final CadastroDePedido cadastroDePedido;
    private final ListagemDePedidoPorNumeroDePedido listarPedidoPorNumeroPedido;
    private final ListagemDePedidoPorStatus listarPedidoPorStatus;
    private final ListagemDePedidoOrdenadosPorRecebimentoEStatus listagemDePedidoOrdenadosPorRecebimentoEStatus;
    private final AtualizacaoDePedido atualizacaoDePedido;

    public PedidoProducaoController(CadastroDePedido cadastroDePedido,
            ListagemDePedidoPorNumeroDePedido listarPedidoPorNumeroPedido,
            ListagemDePedidoPorStatus listarPedidoPorStatus,
            ListagemDePedidoOrdenadosPorRecebimentoEStatus listagemDePedidoOrdenadosPorRecebimentoEStatus,
            AtualizacaoDePedido atualizacaoDePedido, RabbitTemplate rabbitTemplate) {
        this.cadastroDePedido = cadastroDePedido;
        this.listarPedidoPorNumeroPedido = listarPedidoPorNumeroPedido;
        this.listarPedidoPorStatus = listarPedidoPorStatus;
        this.listagemDePedidoOrdenadosPorRecebimentoEStatus =
                listagemDePedidoOrdenadosPorRecebimentoEStatus;
        this.atualizacaoDePedido = atualizacaoDePedido;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/pedido")
    public ResponseEntity<?> enviarPedidoProducao(@RequestBody DadosCadastroPedido dadosCadastroPedido){
        rabbitTemplate.convertAndSend(PEDIDO_PRODUCAO_EX, "", dadosCadastroPedido);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/pedido/cadastro")
    public ResponseEntity<?> cadastroPedidoProducao(@RequestBody DadosPedido dadosPedido){
        Pedido pedido = cadastroDePedido.cadastrarPedido(dadosPedido.convertToPedido());
        return ResponseEntity.ok().body(new DadosPedido(pedido));
    }


    @Operation(summary = "Listar pedido por numero do Pedido")
    @GetMapping("/pedido/{numeroPedido}")
    public ResponseEntity<DadosPedido> listarPedido(@PathVariable String numeroPedido) {

        Pedido pedido = listarPedidoPorNumeroPedido.listarPedidoPorNumeroPedido(numeroPedido);

        return Objects.nonNull(pedido)
                ? ResponseEntity.ok(new DadosPedido(pedido))
                : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Listar pedidos por status")
    @GetMapping("/pedido/status/{status}")
    public ResponseEntity<List<DadosPedido>> listarPedidosPorStatus(
            @PathVariable("status") final StatusPedido statusPedido) {
        List<Pedido> pedidos = listarPedidoPorStatus.listarPedidoPorStatus(statusPedido);
        return ResponseEntity.ok(pedidos.stream().map(DadosPedido::new).collect(Collectors.toList()));
    }

    @Operation(summary = "Listar pedidos ordernados por recebimento e status")
    @GetMapping("/pedido/todos")
    public ResponseEntity<List<DadosPedido>> listarPedidos() {
        List<Pedido> pedidos =
                listagemDePedidoOrdenadosPorRecebimentoEStatus.listarPedidosOrdenadosPorRecebimentoEStatus();
        return ResponseEntity.ok(pedidos.stream().map(DadosPedido::new).collect(Collectors.toList()));
    }

    @Operation(summary = "Atualizar status do pedido")
    @PutMapping("/pedido/{numeroPedido}/statusPedido/{status}")
    public ResponseEntity<DadosPedido> atualizarStatusPedido(
            @PathVariable String numeroPedido, @PathVariable("status") final StatusPedido statusPedido) {
        Pedido pedido = atualizacaoDePedido.atualizarStatusPedido(numeroPedido, statusPedido);
        return ResponseEntity.ok(new DadosPedido(pedido));
    }

    @Operation(summary = "Atualizar status do pagamento no pedido")
    @PutMapping("/pedido/{numeroPedido}/statusPagamento/{statusPagamento}")
    public ResponseEntity<DadosPedido> atualizarStatusPagamentoPedido(
            @PathVariable String numeroPedido, @PathVariable("statusPagamento") final StatusPagamento statusPagamento) {
        Pedido pedido = atualizacaoDePedido.atualizarStatusPagamentoPedido(numeroPedido, statusPagamento);
        return ResponseEntity.ok(new DadosPedido(pedido));
    }

}
