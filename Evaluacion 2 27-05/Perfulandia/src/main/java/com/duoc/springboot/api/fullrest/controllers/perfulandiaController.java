package com.duoc.springboot.api.fullrest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.duoc.springboot.api.fullrest.entities.Usuario;
import com.duoc.springboot.api.fullrest.entities.Pedido;
import com.duoc.springboot.api.fullrest.entities.Producto;
import com.duoc.springboot.api.fullrest.entities.GerenteSucursal;
import com.duoc.springboot.api.fullrest.entities.Sucursal;
import com.duoc.springboot.api.fullrest.repositories.UsuarioRepository;
import com.duoc.springboot.api.fullrest.repositories.PedidoRepository;
import com.duoc.springboot.api.fullrest.repositories.ProductoRepository;
import com.duoc.springboot.api.fullrest.repositories.GerenteSucursalRepository;
import com.duoc.springboot.api.fullrest.repositories.SucursalRepository;

@Controller
public class perfulandiaController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private GerenteSucursalRepository gerenteSucursalRepository;
    
    @Autowired
    private SucursalRepository sucursalRepository;

    @GetMapping("/perfulandia") 
    public String verUsuarios(Model model) {
        // Agregar todos los datos al modelo
        List<Usuario> listadeusuarios = (List<Usuario>) usuarioRepository.findAll();
        List<Pedido> listaDePedidos = (List<Pedido>) pedidoRepository.findAll();
        List<Producto> listaDeProductos = (List<Producto>) productoRepository.findAll();
        List<GerenteSucursal> listaGerentes = (List<GerenteSucursal>) gerenteSucursalRepository.findAll();
        List<Sucursal> listaDeSucursales = (List<Sucursal>) sucursalRepository.findAll();
        
        model.addAttribute("usuarios", listadeusuarios);
        model.addAttribute("pedidos", listaDePedidos);
        model.addAttribute("productos", listaDeProductos);
        model.addAttribute("gerentes", listaGerentes);
        model.addAttribute("sucursales", listaDeSucursales);
        
        return "perfulandia";
    }
}