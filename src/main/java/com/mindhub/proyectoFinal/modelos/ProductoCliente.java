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
    private LocalDateTime fechaCompra;
    private String talle;
    private Integer cantidad;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cliente_id")
    Cliente clienteCompra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="producto_id")
    Producto producto;

    public ProductoCliente() {
    }

    public ProductoCliente( LocalDateTime fechaCompra,String talle,Integer cantidad, Cliente clienteCompra, Producto producto) {
        this.fechaCompra = fechaCompra;
        this.clienteCompra = clienteCompra;
        this.cantidad = cantidad;
        this.producto = producto;
        this.talle = talle;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getTalle() {
        return talle;
    }

    public void setTalle(String talle) {
        this.talle = talle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
