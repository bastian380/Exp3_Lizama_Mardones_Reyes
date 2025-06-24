package com.duoc.springboot.api.fullrest.services;

import java.util.List;
import java.util.Optional;

import com.duoc.springboot.api.fullrest.entities.Sucursal;

public interface SucursalService {

    // Definimos métodos o peticiones para el CRUD

    List<Sucursal> findAll();

    Optional<Sucursal> findById(Long id);

    Sucursal save(Sucursal sucursal);

    Optional<Sucursal> delete(Sucursal sucursal);

}
