package com.duoc.springboot.api.fullrest.services;

import java.util.List;
import java.util.Optional;

import com.duoc.springboot.api.fullrest.entities.GerenteSucursal;

public interface GerenteSucursalService {

    // Métodos CRUD para GerenteSucursal
    
    List<GerenteSucursal> findAll();

    Optional<GerenteSucursal> findById(Long id);

    GerenteSucursal save(GerenteSucursal gerente);

    Optional<GerenteSucursal> delete(GerenteSucursal gerente);
}