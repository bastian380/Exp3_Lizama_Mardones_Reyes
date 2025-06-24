package com.duoc.springboot.api.fullrest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duoc.springboot.api.fullrest.entities.Usuario;
import com.duoc.springboot.api.fullrest.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public Optional<Usuario> delete(Usuario unUsuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(unUsuario.getIdUsuario());
        usuarioOptional.ifPresent(usuarioDb -> {
            usuarioRepository.delete(unUsuario);
        });
        return usuarioOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    @Transactional
    public Usuario save(Usuario unUsuario) {
        return usuarioRepository.save(unUsuario);
    }
}
