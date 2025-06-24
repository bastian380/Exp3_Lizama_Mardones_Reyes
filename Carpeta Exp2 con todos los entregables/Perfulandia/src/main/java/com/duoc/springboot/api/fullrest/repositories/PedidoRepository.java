package com.duoc.springboot.api.fullrest.repositories;

import org.springframework.data.repository.CrudRepository;
import com.duoc.springboot.api.fullrest.entities.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {

}
