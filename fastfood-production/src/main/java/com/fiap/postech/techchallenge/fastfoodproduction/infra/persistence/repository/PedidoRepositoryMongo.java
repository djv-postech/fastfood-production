package com.fiap.postech.techchallenge.fastfoodproduction.infra.persistence.repository;

import com.fiap.postech.techchallenge.fastfoodproduction.core.domain.entities.pedido.StatusPedido;
import com.fiap.postech.techchallenge.fastfoodproduction.infra.persistence.repository.entity.PedidoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepositoryMongo extends MongoRepository<PedidoEntity, String> {
  List<PedidoEntity> findByStatusPedido(StatusPedido statusPedido);

}
