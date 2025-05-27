package com.duoc.springboot.api.fullrest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GerenteSucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_gerente;
    private String nombre_gerente;
    private String telefono_gerente;
    private String correo_gerente;

    public GerenteSucursal() {
    }

    public GerenteSucursal(Long id_gerente, String nombre_gerente, String telefono_gerente, String correo_gerente) {
        this.id_gerente = id_gerente;
        this.nombre_gerente = nombre_gerente;
        this.telefono_gerente = telefono_gerente;
        this.correo_gerente = correo_gerente;
    }

    public Long getId_gerente() {
        return id_gerente;
    }

    public void setId_gerente(Long id_gerente) {
        this.id_gerente = id_gerente;
    }

    public String getNombre_gerente() {
        return nombre_gerente;
    }

    public void setNombre_gerente(String nombre_gerente) {
        this.nombre_gerente = nombre_gerente;
    }

    public String getTelefono_gerente() {
        return telefono_gerente;
    }

    public void setTelefono_gerente(String telefono_gerente) {
        this.telefono_gerente = telefono_gerente;
    }

    public String getCorreo_gerente() {
        return correo_gerente;
    }

    public void setCorreo_gerente(String correo_gerente) {
        this.correo_gerente = correo_gerente;
    }

    @Override
    public String toString() {
        return "GerenteSucursal [id_gerente=" + id_gerente + ", nombre_gerente=" + nombre_gerente +
               ", telefono_gerente=" + telefono_gerente + ", correo_gerente=" + correo_gerente + "]";
    }
}