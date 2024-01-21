# language: pt

  Funcionalidade: Producao

    Cenario: Cadastrar Pedido
      Quando cadastrar um novo pedido
      Entao o pedido é cadastrado
      E deve ser retornado com sucesso

    Cenario: Pesquisar pedido cadastrado
      Dado que um pedido foi cadastrado
      Quando quando buscar pelo numero do pedido
      Entao deve ser retornado com sucesso

   Cenario: Pesquisar pedido que não foi cadastrado no banco de dados
     Quando quando buscar pelo numero do pedido que nao foi cadastrado
     Entao uma mensagem de erro deve ser apresentada

   Cenario: Pesquisar pedidos pelo status do pedido
     Dado que um pedido foi cadastrado
     Quando buscar pedidos pelo status do pedido
     Entao a lista de pedidos é exibida com sucesso

    Cenario: Listar pedidos ordenados por data de recebimento e status
      Dado que um pedido foi cadastrado
      Quando buscar pedidos ordenados por data de recebimento e status
      Entao a lista de pedidos é exibida com sucesso

    Cenario: Atualizar status do pedido
      Dado que um pedido foi cadastrado
      Quando efetuar alteração do status do pedido
      Entao o status do pedido é alterado com sucesso
      E o pedido é retornado


    Cenario: Atualizar status de pagamento do pedido
      Dado que um pedido foi cadastrado
      Quando efetuar alteração do status de pagamento do pedido
      Entao o status de pagamento do pedido é alterado com sucesso
      E o pedido é retornado