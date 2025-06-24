package com.duoc.springboot.api.fullrest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.duoc.springboot.api.fullrest.entities.GerenteSucursal;
import com.duoc.springboot.api.fullrest.repositories.GerenteSucursalRepository;

@Controller
public class GerenteSucursalController {

    @Autowired
    private GerenteSucursalRepository gerenteSucursalRepository;

    @ModelAttribute("gerentes")
    public List<GerenteSucursal> listaDeGerentes() {
        return (List<GerenteSucursal>) gerenteSucursalRepository.findAll();
    }
}
