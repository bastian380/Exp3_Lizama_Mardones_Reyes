package com.duoc.springboot.api.fullrest.ServiceImplTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.duoc.springboot.api.fullrest.entities.Pedido;
import com.duoc.springboot.api.fullrest.repositories.PedidoRepository;
import com.duoc.springboot.api.fullrest.services.PedidoServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PedidoServiceImpltest {

    @InjectMocks
    private PedidoServiceImpl service;

    @Mock
    private PedidoRepository repository;

    private List<Pedido> listaPedidos;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        listaPedidos = new ArrayList<>();
        cargarPedidos();
    }

    @Test
    public void findAllTest() {
        when(repository.findAll()).thenReturn(listaPedidos);

        List<Pedido> response = service.findAll();

        assertEquals(3, response.size());
        verify(repository, times(1)).findAll();
    }

    private void cargarPedidos() {
        Pedido p1 = new Pedido(1L,15000.0, "ENTREGADO", 5L);
        Pedido p2 = new Pedido(2L, 8900.0, "EN CAMINO", 6L);
        Pedido p3 = new Pedido(3L, 21000.0, "ENTREGADO", 7L);
        listaPedidos.add(p1);
        listaPedidos.add(p2);
        listaPedidos.add(p3);
    }
}
