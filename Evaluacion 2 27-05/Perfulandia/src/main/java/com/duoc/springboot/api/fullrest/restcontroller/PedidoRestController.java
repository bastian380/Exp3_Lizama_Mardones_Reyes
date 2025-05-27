package com.duoc.springboot.api.fullrest.restcontroller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.duoc.springboot.api.fullrest.entities.Pedido;
import com.duoc.springboot.api.fullrest.services.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoRestController {
    
    @Autowired
    private PedidoService pedidoService;
    
    @GetMapping
    public List<Pedido> listar() {
        return pedidoService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Pedido> pedidoOptional = pedidoService.findById(id);
        if (pedidoOptional.isPresent()) {
            return ResponseEntity.ok(pedidoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<Pedido> crear(@RequestBody Pedido unPedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(unPedido));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Pedido unPedido) {
        Optional<Pedido> pedidoOptional = pedidoService.findById(id);
        if (pedidoOptional.isPresent()) {
            Pedido pedidoExistente = pedidoOptional.get();
            pedidoExistente.setFecha_pedido(unPedido.getFecha_pedido());
            pedidoExistente.setTotalapagar(unPedido.getTotalapagar());
            pedidoExistente.setEstado(unPedido.getEstado());
            pedidoExistente.setId_cliente(unPedido.getId_cliente());
            Pedido pedidoModificado = pedidoService.save(pedidoExistente);
            return ResponseEntity.ok(pedidoModificado);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Pedido unPedido = new Pedido();
        unPedido.setId_pedido(id);
        Optional<Pedido> pedidoOptional = pedidoService.delete(unPedido);
        if (pedidoOptional.isPresent()) {
            return ResponseEntity.ok(pedidoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
