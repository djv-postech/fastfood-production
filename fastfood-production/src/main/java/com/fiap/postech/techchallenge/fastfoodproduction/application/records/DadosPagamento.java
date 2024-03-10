package com.fiap.postech.techchallenge.fastfoodproduction.application.records;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.Pagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.StatusPagamento;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pagamento.TipoPagamento;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosPagamento(
    String id,
    BigDecimal totalPagamento,
    TipoPagamento tipoPagamento,
    @NotNull @JsonSerialize(using = LocalDateTimeSerializer.class)
    @NotNull @JsonDeserialize(using = LocalDateTimeDeserializer.class)
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
