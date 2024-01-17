package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.cliente;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.vo.CPF;
import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.vo.Email;

public class  Cliente {

  private String nome;
  private CPF cpf;
  private Email email;

  public Cliente(String nome, CPF cpf, Email email) {
    this.nome = nome;
    this.cpf = cpf;
    this.email = email;
  }

  public String getNome() {
    return nome;
  }

  public CPF getCpf() {
    return cpf;
  }

  public Email getEmail() {
    return email;
  }

}
