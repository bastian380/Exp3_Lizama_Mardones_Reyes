package com.duoc.springboot.api.fullrest.ServiceImplTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.duoc.springboot.api.fullrest.entities.Sucursal;
import com.duoc.springboot.api.fullrest.repositories.SucursalRepository;
import com.duoc.springboot.api.fullrest.services.SucursalServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SucursalServiceImplTest {

    @InjectMocks
    private SucursalServiceImpl service;

    @Mock
    private SucursalRepository repository;

    private List<Sucursal> listaSucursales;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        listaSucursales = new ArrayList<>();
        cargarSucursales();
    }

    @Test
    public void findAllTest() {
        when(repository.findAll()).thenReturn(listaSucursales);

        List<Sucursal> response = service.findAll();

        assertEquals(3, response.size());
        verify(repository, times(1)).findAll();
    }

    private void cargarSucursales() {
        Sucursal s1 = new Sucursal(1L, "Sucursal Central", "Santiago", "Av. Principal 123");
        Sucursal s2 = new Sucursal(2L, "Sucursal Norte", "Antofagasta", "Calle Norte 456");
        Sucursal s3 = new Sucursal(3L, "Sucursal Sur", "Puerto Montt", "Calle Sur 789");
        listaSucursales.add(s1);
        listaSucursales.add(s2);
        listaSucursales.add(s3);
    }
}
