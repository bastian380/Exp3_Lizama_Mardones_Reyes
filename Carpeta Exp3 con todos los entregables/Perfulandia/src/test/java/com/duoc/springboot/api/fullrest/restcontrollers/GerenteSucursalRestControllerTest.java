package com.duoc.springboot.api.fullrest.restcontrollers;

import com.duoc.springboot.api.fullrest.entities.GerenteSucursal;
import com.duoc.springboot.api.fullrest.restcontroller.GerenteSucursalRestController;
import com.duoc.springboot.api.fullrest.services.GerenteSucursalService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class GerenteSucursalRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GerenteSucursalService gerenteSucursalService;

    @InjectMocks
    private GerenteSucursalRestController gerenteSucursalRestController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(gerenteSucursalRestController).build();
    }

    @Test
    public void listarGerentesTest() throws Exception {
        List<GerenteSucursal> listaGerentes = List.of(
            new GerenteSucursal(1L, "Juan Perez", "+56912345678", "juan.perez@gmail.com"),
            new GerenteSucursal(2L, "Ana Torres", "+56987654321", "ana.torres@homail.com")
        );

        when(gerenteSucursalService.findAll()).thenReturn(listaGerentes);

        mockMvc.perform(get("/api/gerentes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verUnGerenteTest() throws Exception {
        GerenteSucursal gerente = new GerenteSucursal(1L, "Juan Perez", "+56912345678", "juan.perez@gmail.com");

        when(gerenteSucursalService.findById(1L)).thenReturn(Optional.of(gerente));

        mockMvc.perform(get("/api/gerentes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void gerenteNoExisteTest() throws Exception {
        when(gerenteSucursalService.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/gerentes/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearGerenteTest() throws Exception {
        GerenteSucursal nuevoGerente = new GerenteSucursal(null, "Luis Soto", "+56955555555", "luis.soto@yahoo.com");
        GerenteSucursal gerenteGuardado = new GerenteSucursal(3L, "Luis Soto", "+56955555555", "luis.soto@yahoo.com");

        when(gerenteSucursalService.save(any(GerenteSucursal.class))).thenReturn(gerenteGuardado);

        mockMvc.perform(post("/api/gerentes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevoGerente)))
                .andExpect(status().isCreated());
    }
}
