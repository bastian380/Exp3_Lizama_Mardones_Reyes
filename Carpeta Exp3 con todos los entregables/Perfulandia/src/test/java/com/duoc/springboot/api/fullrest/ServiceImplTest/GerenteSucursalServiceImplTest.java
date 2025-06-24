package com.duoc.springboot.api.fullrest.ServiceImplTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.duoc.springboot.api.fullrest.entities.GerenteSucursal;
import com.duoc.springboot.api.fullrest.repositories.GerenteSucursalRepository;
import com.duoc.springboot.api.fullrest.services.GerenteSucursalServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GerenteSucursalServiceImplTest {

    @InjectMocks
    private GerenteSucursalServiceImpl service;

    @Mock
    private GerenteSucursalRepository repository;

    private List<GerenteSucursal> listaGerentes;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        listaGerentes = new ArrayList<>();
        cargarGerentes();
    }

    @Test
    public void findAllTest() {
        when(repository.findAll()).thenReturn(listaGerentes);

        List<GerenteSucursal> response = service.findAll();

        assertEquals(3, response.size());
        verify(repository, times(1)).findAll();
    }

    private void cargarGerentes() {
        GerenteSucursal g1 = new GerenteSucursal(1L, "Laura Salas", "laura@empresa.com", "clave1");
        GerenteSucursal g2 = new GerenteSucursal(2L, "Miguel Torres", "miguel@empresa.com", "clave2");
        GerenteSucursal g3 = new GerenteSucursal(3L, "Sofía Pérez", "sofia@empresa.com", "clave3");
        listaGerentes.add(g1);
        listaGerentes.add(g2);
        listaGerentes.add(g3);
    }
}
