package com.duoc.springboot.api.fullrest.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duoc.springboot.api.fullrest.entities.Sucursal;
import com.duoc.springboot.api.fullrest.services.SucursalService;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalRestController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public List<Sucursal> listar() {
        return sucursalService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Sucursal> sucursalOptional = sucursalService.findById(id);
        if (sucursalOptional.isPresent()) {
            return ResponseEntity.ok(sucursalOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Sucursal> crear(@RequestBody Sucursal sucursal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalService.save(sucursal));
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Sucursal sucursal = new Sucursal();
        sucursal.setIdSucursal(id);
        Optional<Sucursal> sucursalOptional = sucursalService.delete(sucursal);
        if (sucursalOptional.isPresent()) {
            return ResponseEntity.ok(sucursalOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
