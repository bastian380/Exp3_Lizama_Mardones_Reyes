package com.duoc.springboot.api.fullrest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSucursal;
    private String nombreSucursal;
    private String direccionSucursal;
    private String horariosSucursal;

    public Sucursal() {
    }

    public Sucursal(Long idSucursal, String nombreSucursal, String direccionSucursal, String horariosSucursal) {
        this.idSucursal = idSucursal;
        this.nombreSucursal = nombreSucursal;
        this.direccionSucursal = direccionSucursal;
        this.horariosSucursal = horariosSucursal;
    }

    public Long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getDireccionSucursal() {
        return direccionSucursal;
    }

    public void setDireccionSucursal(String direccionSucursal) {
        this.direccionSucursal = direccionSucursal;
    }

    public String getHorariosSucursal() {
        return horariosSucursal;
    }

    public void setHorariosSucursal(String horariosSucursal) {
        this.horariosSucursal = horariosSucursal;
    }


    @Override
    public String toString() {
        return "Sucursal [idSucursal=" + idSucursal +
               ", nombreSucursal=" + nombreSucursal +
               ", direccionSucursal=" + direccionSucursal +
               ", horariosSucursal=" + horariosSucursal +"]";
    }
}
