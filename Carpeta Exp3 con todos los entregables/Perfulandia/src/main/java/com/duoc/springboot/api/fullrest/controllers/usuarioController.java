package com.duoc.springboot.api.fullrest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.duoc.springboot.api.fullrest.entities.Usuario;
import com.duoc.springboot.api.fullrest.repositories.UsuarioRepository;

@Controller
public class usuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuario")
    public String verUsuarios(Model model) {
        List<Usuario> listaDeUsuarios = (List<Usuario>) usuarioRepository.findAll();
        model.addAttribute("usuarios", listaDeUsuarios);
        return "usuario";
    }
}
