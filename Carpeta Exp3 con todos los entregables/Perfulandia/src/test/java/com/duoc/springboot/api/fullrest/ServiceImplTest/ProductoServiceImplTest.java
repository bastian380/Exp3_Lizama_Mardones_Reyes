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

import com.duoc.springboot.api.fullrest.entities.Producto;
import com.duoc.springboot.api.fullrest.repositories.ProductoRepository;
import com.duoc.springboot.api.fullrest.services.ProductoServiceImpl;

public class ProductoServiceImplTest {

    @InjectMocks
    private ProductoServiceImpl service;

    @Mock
    private ProductoRepository repository;

    private List<Producto> list;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        list = new ArrayList<>();
        chargeProducto();
    }

    @Test
    public void findAllTest() {
        when(repository.findAll()).thenReturn(list);

        List<Producto> response = service.findAll();

        assertEquals(3, response.size());
        verify(repository, times(1)).findAll();
    }

    private void chargeProducto() {
        Producto prod1 = new Producto(1L, "Perfume Rose Elegant", "Fragancia floral con notas de rosa y jazmin", 32990, 30, "Perfumes");
        Producto prod2 = new Producto(2L, "Crema Hidratante Aloe", "Crema facial con extracto natural de aloe", 9990, 50, "Cuidado facial");
        Producto prod3 = new Producto(3L,  "Labial Mate Coral", "Labial de larga duración color coral intenso", 6490, 70, "Maquillaje");
        list.add(prod1);
        list.add(prod2);
        list.add(prod3);
    }
}
