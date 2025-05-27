package com.duoc.springboot.api.fullrest.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duoc.springboot.api.fullrest.entities.Usuario;
import com.duoc.springboot.api.fullrest.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class usuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario unUsuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(unUsuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Usuario unUsuario) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioExistente = usuarioOptional.get();
            usuarioExistente.setNombreUsuario(unUsuario.getNombreUsuario());
            usuarioExistente.setCorreoUsuario(unUsuario.getCorreoUsuario());
            usuarioExistente.setContraseña(unUsuario.getContraseña());
            usuarioExistente.setTipoUsuario(unUsuario.getTipoUsuario());
            usuarioExistente.setFechaCreacion(unUsuario.getFechaCreacion());
            Usuario usuarioModificado = usuarioService.save(usuarioExistente);
            return ResponseEntity.ok(usuarioModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Usuario unUsuario = new Usuario();
        unUsuario.setIdUsuario(id);
        Optional<Usuario> usuarioOptional = usuarioService.delete(unUsuario);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
