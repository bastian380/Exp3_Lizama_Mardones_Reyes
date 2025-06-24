package com.duoc.springboot.api.fullrest.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duoc.springboot.api.fullrest.entities.Sucursal;
import com.duoc.springboot.api.fullrest.services.SucursalService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Sucursales", description = "Operaciones relacionadas con sucursales")
@RestController
@RequestMapping("/api/sucursales")
public class SucursalRestController {

    @Autowired
    private SucursalService sucursalService;

    @Operation(summary = "Obtener lista de sucursales", description = "Devuelve todas las sucursales registradas")
    @ApiResponse(responseCode = "200", description = "Lista de sucursales retornada correctamente",
        content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = Sucursal.class)))
    @GetMapping
    public List<Sucursal> listar() {
        return sucursalService.findAll();
    }

    @Operation(summary = "Obtener sucursal por ID", description = "Obtiene el detalle de una sucursal específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucursal encontrada",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Sucursal.class))),
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Sucursal> sucursalOptional = sucursalService.findById(id);
        return sucursalOptional
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva sucursal", description = "Crea una sucursal con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Sucursal creada correctamente",
        content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = Sucursal.class)))
    @PostMapping
    public ResponseEntity<Sucursal> crear(@RequestBody Sucursal sucursal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalService.save(sucursal));
    }

    @Operation(summary = "Modificar una sucursal", description = "Actualiza los datos de una sucursal existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucursal modificada correctamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Sucursal.class))),
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Sucursal sucursal) {
        Optional<Sucursal> sucursalOptional = sucursalService.findById(id);
        if (sucursalOptional.isPresent()) {
            Sucursal sucursalExistente = sucursalOptional.get();
            sucursalExistente.setNombreSucursal(sucursal.getNombreSucursal());
            sucursalExistente.setDireccionSucursal(sucursal.getDireccionSucursal());
            sucursalExistente.setHorariosSucursal(sucursal.getHorariosSucursal());
            Sucursal sucursalModificada = sucursalService.save(sucursalExistente);
            return ResponseEntity.ok(sucursalModificada);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar una sucursal", description = "Elimina una sucursal por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucursal eliminada correctamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Sucursal.class))),
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Sucursal sucursal = new Sucursal();
        sucursal.setIdSucursal(id);
        Optional<Sucursal> sucursalOptional = sucursalService.delete(sucursal);
        return sucursalOptional
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

