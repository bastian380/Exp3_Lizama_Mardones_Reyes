package com.duoc.springboot.api.fullrest.services;

import java.util.List;
import java.util.Optional;

import com.duoc.springboot.api.fullrest.entities.Producto;

public interface productoService {

    // Métodos CRUD para Producto
    
    List<Producto> findAll();
    
    Optional<Producto> findById(Long id);
    
    Producto save(Producto producto);
    
    Optional<Producto> delete(Producto producto);
}