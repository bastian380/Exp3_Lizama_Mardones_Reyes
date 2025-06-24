package com.duoc.springboot.api.fullrest.restcontrollers;

import com.duoc.springboot.api.fullrest.entities.Producto;
import com.duoc.springboot.api.fullrest.restcontroller.ProductoRestController;
import com.duoc.springboot.api.fullrest.services.ProductoServiceImpl;
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
public class ProductoRestControllersTest {

    private MockMvc mockMvc;

    @Mock
    private ProductoServiceImpl productoService;

    @InjectMocks
    private ProductoRestController productoRestController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(productoRestController).build();
    }

    @Test
    public void verProductosTest() throws Exception {
        List<Producto> productosLista = List.of(
            new Producto(1L, "Perfume Rose Elegant", "Fragancia floral con notas de rosa y jazmín", 32990, 30, "Perfumes"),
            new Producto(2L, "Crema Hidratante Aloe", "Crema facial con extracto natural de aloe", 9990, 50, "Cuidado facial"),
            new Producto(3L, "Labial Mate Coral", "Labial de larga duración color coral intenso", 6490, 70, "Maquillaje")
        );

        when(productoService.findAll()).thenReturn(productosLista);

        mockMvc.perform(get("/api/productos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verUnProductoTest() throws Exception {
        Producto unProducto = new Producto(1L, "Perfume Rose Elegant", "Fragancia floral con notas de rosa y jazmín", 32990, 30, "Perfumes");
        when(productoService.findById(1L)).thenReturn(Optional.of(unProducto));

        mockMvc.perform(get("/api/productos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void productoNoExisteTest() throws Exception {
        when(productoService.findById(10L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/productos/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearProductoTest() throws Exception {
        Producto unProducto = new Producto(null, "Crema Hidratante Aloe", "Crema facial con extracto natural de aloe", 9990, 50, "Cuidado facial");
        Producto otroProducto = new Producto(4L, "Crema Hidratante Aloe", "Crema facial con extracto natural de aloe", 9990, 50, "Cuidado facial");

        when(productoService.save(any(Producto.class))).thenReturn(otroProducto);

        mockMvc.perform(post("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unProducto)))
                .andExpect(status().isCreated());
    }
}
