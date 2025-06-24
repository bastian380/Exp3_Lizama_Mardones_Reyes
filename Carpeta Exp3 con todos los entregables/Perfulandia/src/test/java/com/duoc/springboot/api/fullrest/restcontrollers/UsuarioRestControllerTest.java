package com.duoc.springboot.api.fullrest.restcontrollers;

import com.duoc.springboot.api.fullrest.entities.Usuario;
import com.duoc.springboot.api.fullrest.restcontroller.usuarioRestController;
import com.duoc.springboot.api.fullrest.services.UsuarioService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UsuarioRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private usuarioRestController usuarioRestController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioRestController).build();
    }

    @Test
    public void listarUsuariosTest() throws Exception {
        List<Usuario> listaUsuarios = List.of(
            new Usuario(1L, "Carlos Perez", "carlos@yahoo.com", "1234", "user"),
            new Usuario(2L, "Ana Diaz", "ana@hotmail.com", "abcd", "admin"),
            new Usuario(3L, "Luis Soto", "juan@gmail.com", "xyz", "user")
        );

        when(usuarioService.findAll()).thenReturn(listaUsuarios);

        mockMvc.perform(get("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verUnUsuarioTest() throws Exception {
        Usuario usuario = new Usuario(1L, "Carlos Perez", "carlos@yahoo.com", "1234", "user");

        when(usuarioService.findById(1L)).thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/api/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void usuarioNoExisteTest() throws Exception {
        when(usuarioService.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/usuarios/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearUsuarioTest() throws Exception {
        Usuario nuevoUsuario = new Usuario(null, "Carlos Perez", "carlos@yahoo.com", "1234", "user");
        Usuario usuarioGuardado = new Usuario(4L, "Carlos Perez", "carlos@yahoo.com", "1234", "user");

        when(usuarioService.save(any(Usuario.class))).thenReturn(usuarioGuardado);

        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevoUsuario)))
                .andExpect(status().isCreated());
    }
}

