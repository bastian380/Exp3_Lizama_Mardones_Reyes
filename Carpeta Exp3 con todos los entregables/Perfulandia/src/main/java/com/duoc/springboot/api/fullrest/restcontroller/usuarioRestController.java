package com.duoc.springboot.api.fullrest.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duoc.springboot.api.fullrest.entities.Usuario;
import com.duoc.springboot.api.fullrest.services.UsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios del sistema")
@RestController
@RequestMapping("/api/usuarios")
public class usuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Listar todos los usuarios", description = "Obtiene una lista completa de usuarios")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente",
        content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = Usuario.class)))
    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.findAll();
    }

    @Operation(summary = "Buscar usuario por ID", description = "Retorna un usuario según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        return usuarioOptional
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nuevo usuario", description = "Registra un nuevo usuario en el sistema")
    @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente",
        content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = Usuario.class)))
    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario unUsuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(unUsuario));
    }

    @Operation(summary = "Modificar usuario existente", description = "Actualiza los datos de un usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario modificado correctamente",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Usuario unUsuario) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioExistente = usuarioOptional.get();
            usuarioExistente.setNombreUsuario(unUsuario.getNombreUsuario());
            usuarioExistente.setCorreoUsuario(unUsuario.getCorreoUsuario());
            usuarioExistente.setContraseña(unUsuario.getContraseña());
            usuarioExistente.setTipoUsuario(unUsuario.getTipoUsuario());

            Usuario usuarioModificado = usuarioService.save(usuarioExistente);
            return ResponseEntity.ok(usuarioModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario existente según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Usuario unUsuario = new Usuario();
        unUsuario.setIdUsuario(id);
        Optional<Usuario> usuarioOptional = usuarioService.delete(unUsuario);
        return usuarioOptional
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
