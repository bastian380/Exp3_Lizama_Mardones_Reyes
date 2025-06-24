package com.duoc.springboot.api.fullrest.services;

import java.util.List;
import java.util.Optional;

import com.duoc.springboot.api.fullrest.entities.Pedido;

public interface PedidoService {

    // Definimos métodos o peticiones para el CRUD

    List<Pedido> findAll();

    Optional<Pedido> findById(Long id);

    Pedido save(Pedido unPedido);

    Optional<Pedido> delete(Pedido unPedido);
}
