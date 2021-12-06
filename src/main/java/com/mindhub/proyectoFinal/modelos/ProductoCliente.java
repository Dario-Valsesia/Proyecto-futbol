package com.mindhub.proyectoFinal.modelos;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ProductoCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private Integer numCompra;
    private LocalDateTime fechaCompra;
    private String talle;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cliente_id")
    Cliente clienteCompra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="producto_id")
    Producto producto;

    public ProductoCliente() {
    }

    public ProductoCliente(Integer numCompra, LocalDateTime fechaCompra,String talle, Cliente clienteCompra, Producto producto) {
        this.numCompra = numCompra;
        this.fechaCompra = fechaCompra;
        this.clienteCompra = clienteCompra;
        this.producto = producto;
        this.talle = talle;
    }

    public String getTalle() {
        return talle;
    }

    public void setTalle(String talle) {
        this.talle = talle;
    }

    public Integer getNumCompra() {
        return numCompra;
    }

    public void setNumCompra(Integer numCompra) {
        this.numCompra = numCompra;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Cliente getClienteCompra() {
        return clienteCompra;
    }

    public void setClienteCompra(Cliente clienteCompra) {
        this.clienteCompra = clienteCompra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
