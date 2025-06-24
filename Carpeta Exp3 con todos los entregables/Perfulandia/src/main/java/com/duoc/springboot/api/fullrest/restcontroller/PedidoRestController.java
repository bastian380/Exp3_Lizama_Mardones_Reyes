package com.duoc.springboot.api.fullrest.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duoc.springboot.api.fullrest.entities.Pedido;
import com.duoc.springboot.api.fullrest.services.PedidoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "Pedidos", description = "Operaciones relacionadas con pedidos")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoRestController {

    @Autowired
    private PedidoService pedidoService;

    @Operation(summary = "Obtener lista de pedidos", description = "Devuelve todos los pedidos registrados")
    @ApiResponse(responseCode = "200", description = "Lista de pedidos retornada correctamente",
        content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = Pedido.class)))
    @GetMapping
    public List<Pedido> listar() {
        return pedidoService.findAll();
    }

    @Operation(summary = "Obtener pedido por ID", description = "Obtiene el detalle de un pedido específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido encontrado",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Pedido> pedidoOptional = pedidoService.findById(id);
        return pedidoOptional
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo pedido", description = "Crea un pedido con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Pedido creado correctamente",
        content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = Pedido.class)))
    @PostMapping
    public ResponseEntity<Pedido> crear(@RequestBody Pedido unPedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(unPedido));
    }

    @Operation(summary = "Modificar un pedido existente", description = "Actualiza los datos de un pedido por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido modificado correctamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Pedido unPedido) {
        Optional<Pedido> pedidoOptional = pedidoService.findById(id);
        if (pedidoOptional.isPresent()) {
            Pedido pedidoExistente = pedidoOptional.get();
            pedidoExistente.setTotalapagar(unPedido.getTotalapagar());
            pedidoExistente.setEstado(unPedido.getEstado());
            pedidoExistente.setId_cliente(unPedido.getId_cliente());
            Pedido pedidoModificado = pedidoService.save(pedidoExistente);
            return ResponseEntity.ok(pedidoModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un pedido", description = "Elimina un pedido según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido eliminado correctamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Pedido unPedido = new Pedido();
        unPedido.setId_pedido(id);
        Optional<Pedido> pedidoOptional = pedidoService.delete(unPedido);
        return pedidoOptional
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
