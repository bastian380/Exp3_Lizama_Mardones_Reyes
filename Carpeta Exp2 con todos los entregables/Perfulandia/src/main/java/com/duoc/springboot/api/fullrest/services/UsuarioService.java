package com.duoc.springboot.api.fullrest.services;

import java.util.List;
import java.util.Optional;

import com.duoc.springboot.api.fullrest.entities.Usuario;

public interface UsuarioService {

    // Definimos métodos o peticiones para el CRUD

    List<Usuario> findAll();

    Optional<Usuario> findById(Long id);

    Usuario save(Usuario unUsuario);

    Optional<Usuario> delete(Usuario unUsuario);

}
