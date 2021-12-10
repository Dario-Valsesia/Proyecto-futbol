package com.mindhub.proyectoFinal.dtos;

import com.mindhub.proyectoFinal.modelos.ProductoCliente;

import java.time.LocalDateTime;

public class ProductoClienteDTO {
    private long id;
    private LocalDateTime fechaCompra;
    private String nameProducto;
    private String talle;
    private String marca;
    private Integer cantidad;

    public ProductoClienteDTO() {
    }

    public ProductoClienteDTO(ProductoCliente productoCliente) {
        this.id = productoCliente.getId();
        this.fechaCompra = productoCliente.getFechaCompra();
        this.nameProducto = productoCliente.getProducto().getNombreProducto();
        this.talle = productoCliente.getTalle();
        this.marca = productoCliente.getProducto().getMarca();
        this.cantidad= productoCliente.getCantidad();
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
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
