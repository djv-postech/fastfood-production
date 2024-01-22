package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class CPFTest {

    @Test
    public void deveCriarUmObjetoDeCPFComFormatacaoValida(){
        CPF cpf = new CPF("333.333.333-33");
        Assertions.assertThat(cpf).isInstanceOf(CPF.class);
    }

    @Test
    public void deveGerarExcecaoAoInstanciarUmCPFComFormatacaoInvalida(){
        Assertions.assertThatThrownBy(() -> new CPF("33333333333"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("CPF inválido");
    }

    @Test
    public void deveGerarExcecaoAoInstanciarUmCPFNull(){
        String numero = null;
        Assertions.assertThatThrownBy(() -> new CPF(numero))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("CPF inválido");
    }
}