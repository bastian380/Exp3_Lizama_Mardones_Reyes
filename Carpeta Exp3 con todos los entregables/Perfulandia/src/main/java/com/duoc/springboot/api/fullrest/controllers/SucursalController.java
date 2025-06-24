package com.duoc.springboot.api.fullrest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.duoc.springboot.api.fullrest.entities.Sucursal;
import com.duoc.springboot.api.fullrest.repositories.SucursalRepository;

@Controller
public class SucursalController {

    @Autowired
    private SucursalRepository sucursalRepository;

    @ModelAttribute("sucursales")
    public List<Sucursal> listaDeSucursales() {
        return (List<Sucursal>) sucursalRepository.findAll();
    }
}
