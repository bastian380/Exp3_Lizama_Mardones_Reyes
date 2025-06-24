package com.duoc.springboot.api.fullrest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pedido;
    private Double totalapagar;
    private String estado;
    private Long id_cliente;
    
    
    public Pedido(Long id_pedido, Double totalapagar, String estado, Long id_cliente) {
        this.id_pedido = id_pedido;
        this.totalapagar = totalapagar;
        this.estado = estado;
        this.id_cliente = id_cliente;
    }
    


    public Pedido() {
    }

    public Long getId_pedido() {
        return id_pedido;
    }
    
    public void setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
    }
    
    public Double getTotalapagar() {
        return totalapagar;
    }
    
    public void setTotalapagar(Double totalapagar) {
        this.totalapagar = totalapagar;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public Long getId_cliente() {
        return id_cliente;
    }
    
    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }
    
    @Override
    public String toString() {
        return "Pedido [id_pedido=" + id_pedido + ", fecha_pedido=" +
               ", totalapagar=" + totalapagar + ", estado=" + estado + ", id_cliente=" + id_cliente + "]";
    }
}