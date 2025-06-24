package com.duoc.springboot.api.fullrest.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duoc.springboot.api.fullrest.entities.GerenteSucursal;
import com.duoc.springboot.api.fullrest.services.GerenteSucursalService;

@RestController
@RequestMapping("/api/gerentes")
public class GerenteSucursalRestController {

    @Autowired
    private GerenteSucursalService gerenteSucursalService;

    @GetMapping
    public List<GerenteSucursal> listar() {
        return gerenteSucursalService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<GerenteSucursal> gerenteOptional = gerenteSucursalService.findById(id);
        if (gerenteOptional.isPresent()) {
            return ResponseEntity.ok(gerenteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<GerenteSucursal> crear(@RequestBody GerenteSucursal gerente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gerenteSucursalService.save(gerente));
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        GerenteSucursal gerente = new GerenteSucursal();
        gerente.setId_gerente(id);
        Optional<GerenteSucursal> gerenteOptional = gerenteSucursalService.delete(gerente);
        if (gerenteOptional.isPresent()) {
            return ResponseEntity.ok(gerenteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}