package com.fiap.postech.techchallenge.fastfoodproduction.application.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


public record DadosCadastroCliente(String nome, @NotNull String cpf, @Email(message = "E-mail inválido") String email) {
    
}
