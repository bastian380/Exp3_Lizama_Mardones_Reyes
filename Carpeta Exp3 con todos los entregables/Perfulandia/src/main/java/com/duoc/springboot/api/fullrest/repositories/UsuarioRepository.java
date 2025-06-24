package com.duoc.springboot.api.fullrest.repositories;

import org.springframework.data.repository.CrudRepository;
import com.duoc.springboot.api.fullrest.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
