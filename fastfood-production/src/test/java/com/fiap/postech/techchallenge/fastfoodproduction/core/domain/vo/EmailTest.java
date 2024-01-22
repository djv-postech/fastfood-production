package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.vo;

import com.fiap.postech.techchallenge.fastfoodproduction.infra.persistence.exception.NotFoundException;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class EmailTest {

    @Test
    public void deveCriarUmObjetoDeEmailValido(){
        Email email = new Email("teste@teste.com");
        Assertions.assertThat(email).isInstanceOf(Email.class);
    }

    @Test
    public void deveGerarExcecaoAoInstanciarUmEmailInvalido(){
        Assertions.assertThatThrownBy(() -> new Email("teste"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Email inválido");
    }

    @Test
    public void deveGerarExcecaoAoInstanciarUmEmailNull(){
        String email = null;
        Assertions.assertThatThrownBy(() -> new Email(email))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Email inválido");
    }

}