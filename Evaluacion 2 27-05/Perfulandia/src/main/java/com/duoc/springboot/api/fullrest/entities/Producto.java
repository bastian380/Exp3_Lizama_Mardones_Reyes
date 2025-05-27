package com.duoc.springboot.api.fullrest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private double precioProducto;
    private int cantidadStock;
    private LocalDateTime fechaIngreso;
    private String categoria;

    public Producto() {
    }

    public Producto(Long idProducto, String nombreProducto, String descripcionProducto, 
                   double precioProducto, int cantidadStock, LocalDateTime fechaIngreso, 
                   String categoria) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.precioProducto = precioProducto;
        this.cantidadStock = cantidadStock;
        this.fechaIngreso = fechaIngreso;
        this.categoria = categoria;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Producto [idProducto=" + idProducto + ", nombreProducto=" + nombreProducto +
               ", descripcionProducto=" + descripcionProducto + ", precioProducto=" + precioProducto +
               ", cantidadStock=" + cantidadStock + ", fechaIngreso=" + fechaIngreso + 
               ", categoria=" + categoria + "]";
    }
}