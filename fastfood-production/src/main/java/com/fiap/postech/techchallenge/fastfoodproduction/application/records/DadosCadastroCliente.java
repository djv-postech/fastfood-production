package com.fiap.postech.techchallenge.fastfoodproduction.application.records;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.cliente.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


public record DadosCadastroCliente(String nome, @NotNull String cpf, @Email(message = "E-mail inválido") String email) {

    public Cliente convertToCliente() {
        return new Cliente(nome, new com.fiap.postech.techchallenge.fastfoodproduction.core.domain.vo.CPF(cpf),
                new com.fiap.postech.techchallenge.fastfoodproduction.core.domain.vo.Email(email));
    }
}
