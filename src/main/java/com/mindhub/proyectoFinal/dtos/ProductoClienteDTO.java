package com.mindhub.proyectoFinal.dtos;

import com.mindhub.proyectoFinal.modelos.ProductoCliente;

import java.time.LocalDateTime;

public class ProductoClienteDTO {
    private long id;
    private Integer numCompra;
    private LocalDateTime fechaCompra;
    private String nameProducto;
    private String talle;
    private String marca;

    public ProductoClienteDTO() {
    }

    public ProductoClienteDTO(ProductoCliente productoCliente) {
        this.id = productoCliente.getId();
        this.numCompra = productoCliente.getNumCompra();
        this.fechaCompra = productoCliente.getFechaCompra();
        this.nameProducto = productoCliente.getProducto().getName();
        this.talle = productoCliente.getTalle();
        this.marca = productoCliente.getProducto().getMarca();
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getNameProducto() {
        return nameProducto;
    }

    public void setNameProducto(String nameProducto) {
        this.nameProducto = nameProducto;
    }

    public String getTalle() {
        return talle;
    }

    public void setTalle(String talle) {
        this.talle = talle;
    }
}
