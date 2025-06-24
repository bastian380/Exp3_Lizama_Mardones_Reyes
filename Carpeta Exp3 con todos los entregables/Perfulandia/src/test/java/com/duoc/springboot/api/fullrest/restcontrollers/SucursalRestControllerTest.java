package com.duoc.springboot.api.fullrest.restcontrollers;

import com.duoc.springboot.api.fullrest.entities.Sucursal;
import com.duoc.springboot.api.fullrest.restcontroller.SucursalRestController;
import com.duoc.springboot.api.fullrest.services.SucursalService;
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
public class SucursalRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SucursalService sucursalService;

    @InjectMocks
    private SucursalRestController sucursalRestController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(sucursalRestController).build();
    }

    @Test
    public void listarSucursalesTest() throws Exception {
        List<Sucursal> sucursales = List.of(
            new Sucursal(1L, "Sucursal Centro", "Av. Alameda 123", "09:00 - 18:00"),
            new Sucursal(2L, "Sucursal Maipú", "Av. Pajaritos 456", "10:00 - 19:00")
        );

        when(sucursalService.findAll()).thenReturn(sucursales);

        mockMvc.perform(get("/api/sucursales")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verUnaSucursalTest() throws Exception {
        Sucursal sucursal = new Sucursal(1L, "Sucursal Centro", "Av. Alameda 123", "09:00 - 18:00");

        when(sucursalService.findById(1L)).thenReturn(Optional.of(sucursal));

        mockMvc.perform(get("/api/sucursales/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void sucursalNoExisteTest() throws Exception {
        when(sucursalService.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/sucursales/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearSucursalTest() throws Exception {
        Sucursal nuevaSucursal = new Sucursal(4L, "Sucursal Ñuñoa", "Av. Irarrazaval 789", "08:00 - 17:00");
        Sucursal sucursalGuardada = new Sucursal(3L, "Sucursal Ñuñoa", "Av. Irarrazaval 789", "08:00 - 17:00");

        when(sucursalService.save(any(Sucursal.class))).thenReturn(sucursalGuardada);

        mockMvc.perform(post("/api/sucursales")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevaSucursal)))
                .andExpect(status().isCreated());
    }
}
