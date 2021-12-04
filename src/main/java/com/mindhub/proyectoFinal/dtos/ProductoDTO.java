package com.mindhub.proyectoFinal.dtos;


import com.mindhub.proyectoFinal.modelos.Botin;
import com.mindhub.proyectoFinal.modelos.Camiseta;
import com.mindhub.proyectoFinal.modelos.Medias;
import com.mindhub.proyectoFinal.modelos.Producto;

import javax.print.attribute.standard.Media;

public class ProductoDTO {
    private Long id;
    private String name;
    private double costo, precio;
    private int stock;
    private String marca;
    private String[] talle;
    //camiseta
    private String equipo;
    //botin
    private String tipo;

    public ProductoDTO() {
    }

    public ProductoDTO(Camiseta camiseta){
        this.id = camiseta.getId();
        this.name = camiseta.getName();
        this.costo= camiseta.getCosto();
        this.precio= camiseta.getPrecio();
        this.stock= camiseta.getStock();
        this.marca= camiseta.getMarca();
        this.talle= camiseta.getTalle();
        this.equipo= camiseta.getEquipo();
        //this.tipo=null;
    }
    public ProductoDTO(Botin botin) {
        this.id = botin.getId();
        this.name = botin.getName();
        this.costo = botin.getCosto();
        this.precio = botin.getPrecio();
        this.stock = botin.getStock();
        this.marca = botin.getMarca();
        this.talle = botin.getTalle();
        this.tipo = botin.getTipo();
        //this.equipo = null;
    }
    public ProductoDTO(Medias medias){
            this.id = medias.getId();
            this.name = medias.getName();
            this.costo= medias.getCosto();
            this.precio= medias.getPrecio();
            this.stock= medias.getStock();
            this.marca= medias.getMarca();
            this.talle= medias.getTalle();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String[] getTalle() {
        return talle;
    }

    public void setTalle(String[] talle) {
        this.talle = talle;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
