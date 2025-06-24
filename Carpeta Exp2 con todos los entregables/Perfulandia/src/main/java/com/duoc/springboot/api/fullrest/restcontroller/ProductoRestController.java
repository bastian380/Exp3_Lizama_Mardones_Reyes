package com.duoc.springboot.api.fullrest.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duoc.springboot.api.fullrest.entities.Producto;
import com.duoc.springboot.api.fullrest.services.productoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

    @Autowired
    private productoService productoService;

    @GetMapping
    public List<Producto> listar() {
        return productoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Producto> productoOptional = productoService.findById(id);
        if (productoOptional.isPresent()) {
            return ResponseEntity.ok(productoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Producto producto) {
        Optional<Producto> productoOptional = productoService.findById(id);
        if (productoOptional.isPresent()) {
            Producto productoExistente = productoOptional.get();
            productoExistente.setNombreProducto(producto.getNombreProducto());
            productoExistente.setDescripcionProducto(producto.getDescripcionProducto());
            productoExistente.setPrecioProducto(producto.getPrecioProducto());
            productoExistente.setCantidadStock(producto.getCantidadStock());
            productoExistente.setFechaIngreso(producto.getFechaIngreso());
            productoExistente.setCategoria(producto.getCategoria());
            
            Producto productoModificado = productoService.save(productoExistente);
            return ResponseEntity.ok(productoModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Producto producto = new Producto();
        producto.setIdProducto(id);
        Optional<Producto> productoOptional = productoService.delete(producto);
        if (productoOptional.isPresent()) {
            return ResponseEntity.ok(productoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}