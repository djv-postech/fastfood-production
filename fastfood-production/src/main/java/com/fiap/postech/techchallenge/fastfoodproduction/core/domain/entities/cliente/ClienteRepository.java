package com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.cliente;

import java.util.List;

public interface ClienteRepository {

  Cliente cadastrarCliente(Cliente cliente);

  Cliente identificaClientePorCpf(String cpf);

  Cliente atualizarCliente(Cliente cliente);

  List<Cliente> listarClientes();

  void excluirClientePorCpf(String cpf);
}
