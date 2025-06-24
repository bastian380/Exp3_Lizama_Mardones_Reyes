package com.duoc.springboot.api.fullrest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.duoc.springboot.api.fullrest.entities.Pedido;
import com.duoc.springboot.api.fullrest.repositories.PedidoRepository;

@Controller
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @ModelAttribute("pedidos")
    public List<Pedido> listaDePedidos() {
        return (List<Pedido>) pedidoRepository.findAll();
    }
}
