package com.duoc.springboot.api.fullrest.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duoc.springboot.api.fullrest.entities.GerenteSucursal;
import com.duoc.springboot.api.fullrest.services.GerenteSucursalService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Gerentes", description = "Operaciones relacionadas con los gerentes de sucursal")
@RestController
@RequestMapping("/api/gerentes")
public class GerenteSucursalRestController {

    @Autowired
    private GerenteSucursalService gerenteSucursalService;

    @Operation(summary = "Obtener lista de gerentes", description = "Devuelve todos los gerentes registrados")
    @ApiResponse(responseCode = "200", description = "Lista de gerentes retornada correctamente",
        content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = GerenteSucursal.class)))
    @GetMapping
    public List<GerenteSucursal> listar() {
        return gerenteSucursalService.findAll();
    }

    @Operation(summary = "Obtener gerente por ID", description = "Obtiene los datos de un gerente específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Gerente encontrado",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = GerenteSucursal.class))),
        @ApiResponse(responseCode = "404", description = "Gerente no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<GerenteSucursal> gerenteOptional = gerenteSucursalService.findById(id);
        return gerenteOptional
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nuevo gerente", description = "Registra un nuevo gerente con los datos entregados")
    @ApiResponse(responseCode = "201", description = "Gerente creado correctamente",
        content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = GerenteSucursal.class)))
    @PostMapping
    public ResponseEntity<GerenteSucursal> crear(@RequestBody GerenteSucursal gerente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gerenteSucursalService.save(gerente));
    }

    @Operation(summary = "Modificar gerente existente", description = "Modifica los datos de un gerente ya registrado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Gerente modificado correctamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = GerenteSucursal.class))),
        @ApiResponse(responseCode = "404", description = "Gerente no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody GerenteSucursal gerente) {
        Optional<GerenteSucursal> gerenteOptional = gerenteSucursalService.findById(id);
        if (gerenteOptional.isPresent()) {
            GerenteSucursal gerenteExistente = gerenteOptional.get();
            gerenteExistente.setNombre_gerente(gerente.getNombre_gerente());
            gerenteExistente.setTelefono_gerente(gerente.getTelefono_gerente());
            gerenteExistente.setCorreo_gerente(gerente.getCorreo_gerente());

            GerenteSucursal gerenteModificado = gerenteSucursalService.save(gerenteExistente);
            return ResponseEntity.ok(gerenteModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar gerente", description = "Elimina un gerente a partir de su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Gerente eliminado correctamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = GerenteSucursal.class))),
        @ApiResponse(responseCode = "404", description = "Gerente no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        GerenteSucursal gerente = new GerenteSucursal();
        gerente.setId_gerente(id);
        Optional<GerenteSucursal> gerenteOptional = gerenteSucursalService.delete(gerente);
        return gerenteOptional
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
