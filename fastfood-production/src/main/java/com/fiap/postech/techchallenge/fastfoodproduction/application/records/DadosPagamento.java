package com.fiap.postech.techchallenge.fastfoodproduction.application.records;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.Pagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.StatusPagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.TipoPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosPagamento(
    String id,
    BigDecimal totalPagamento,
    TipoPagamento tipoPagamento,
    LocalDateTime dataPagamento,
    StatusPagamento statusPagamento) {

  public DadosPagamento(Pagamento dadosPagamento) {
    this(
        dadosPagamento.getId(),
        dadosPagamento.getTotalPagamento(),
        dadosPagamento.getTipoPagamento(),
        dadosPagamento.getDataEHorarioPagamento(),
        dadosPagamento.getStatusPagamento());
  }
}
