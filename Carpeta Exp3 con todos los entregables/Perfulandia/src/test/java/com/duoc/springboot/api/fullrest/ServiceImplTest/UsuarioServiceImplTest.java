package com.duoc.springboot.api.fullrest.ServiceImplTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.duoc.springboot.api.fullrest.entities.Usuario;
import com.duoc.springboot.api.fullrest.repositories.UsuarioRepository;
import com.duoc.springboot.api.fullrest.services.UsuarioServiceImpl;

public class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl service;

    @Mock
    private UsuarioRepository repository;

    private List<Usuario> listaUsuarios;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        listaUsuarios = new ArrayList<>();
        cargarUsuarios();
    }

    @Test
    public void findAllTest() {
        when(repository.findAll()).thenReturn(listaUsuarios);

        List<Usuario> response = service.findAll();

        assertEquals(3, response.size());
        verify(repository, times(1)).findAll();
    }

    private void cargarUsuarios() {
        Usuario u1 = new Usuario(1L, "Carlos Perez", "carlos@yahoo.com", "1234", "user");
        Usuario u2 = new Usuario(2L, "Ana Diaz", "ana@hotmail.com", "abcd", "admin");
        Usuario u3 = new Usuario(3L, "Luis Soto", "juan@gmail.com", "xyz", "user");
        listaUsuarios.add(u1);
        listaUsuarios.add(u2);
        listaUsuarios.add(u3);
    }
}

