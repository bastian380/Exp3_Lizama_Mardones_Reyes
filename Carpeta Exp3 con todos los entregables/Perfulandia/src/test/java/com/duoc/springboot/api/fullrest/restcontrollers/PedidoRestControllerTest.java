package com.duoc.springboot.api.fullrest.restcontrollers;

import com.duoc.springboot.api.fullrest.entities.Pedido;
import com.duoc.springboot.api.fullrest.restcontroller.PedidoRestController;
import com.duoc.springboot.api.fullrest.services.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class PedidoRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoRestController pedidoRestController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoRestController).build();
    }

    @Test
    public void listarPedidosTest() throws Exception {
        List<Pedido> listaPedidos = List.of(
            new Pedido(10L,15000.0, "ENTREGADO", 5L),
            new Pedido(10L,15000.0, "ENTREGADO", 5L)
        );

        when(pedidoService.findAll()).thenReturn(listaPedidos);

        mockMvc.perform(get("/api/pedidos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verUnPedidoTest() throws Exception {
        Pedido pedido = new Pedido(13L,19000.0, "ENTREGADO", 89L);

        when(pedidoService.findById(1L)).thenReturn(Optional.of(pedido));

        mockMvc.perform(get("/api/pedidos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void pedidoNoExisteTest() throws Exception {
        when(pedidoService.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/pedidos/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearPedidoTest() throws Exception {
        Pedido nuevoPedido = new Pedido(11L,16000.0, "ENTREGADO", 9L);
        Pedido pedidoGuardado = new Pedido(11L,16000.0, "ENTREGADO", 9L);

        when(pedidoService.save(any(Pedido.class))).thenReturn(pedidoGuardado);

        mockMvc.perform(post("/api/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevoPedido)))
                .andExpect(status().isCreated());
    }
}
