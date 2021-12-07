package com.mindhub.proyectoFinal.dtos;


import com.mindhub.proyectoFinal.modelos.*;
import com.mindhub.proyectoFinal.modelos.Short;

import javax.print.attribute.standard.Media;

public class ProductoDTO {
    private Long id;
    private String name;
    private String nombreProducto;
    private double costo, precio;
    private int stock;
    private String marca;
    private String[] talle;
    private String urlImg;
    //camiseta
    private String equipo;
    //botin
    private String tipo;

    public ProductoDTO() {
    }

    public ProductoDTO(Camiseta camiseta){
        this.id = camiseta.getId();
        this.name = camiseta.getName();
        this.nombreProducto = camiseta.getNombreProducto();
        this.costo= camiseta.getCosto();
        this.precio= camiseta.getPrecio();
        this.stock= camiseta.getStock();
        this.marca= camiseta.getMarca();
        this.talle= camiseta.getTalle();
        this.urlImg = camiseta.getUrlImg();
        this.equipo= camiseta.getEquipo();
    }
    public ProductoDTO(Botin botin) {
        this.id = botin.getId();
        this.name = botin.getName();
        this.nombreProducto = botin.getNombreProducto();
        this.costo = botin.getCosto();
        this.precio = botin.getPrecio();
        this.stock = botin.getStock();
        this.marca = botin.getMarca();
        this.talle = botin.getTalle();
        this.urlImg = botin.getUrlImg();
        this.tipo = botin.getTipo();
    }
    public ProductoDTO(Medias medias){
            this.id = medias.getId();
            this.name = medias.getName();
            this.nombreProducto = medias.getNombreProducto();
            this.costo= medias.getCosto();
            this.precio= medias.getPrecio();
            this.stock= medias.getStock();
            this.marca= medias.getMarca();
            this.talle= medias.getTalle();
            this.urlImg = medias.getUrlImg();
    }
    public ProductoDTO(Pelota pelota){
        this.id = pelota.getId();
        this.name = pelota.getName();
        this.nombreProducto = pelota.getNombreProducto();
        this.costo= pelota.getCosto();
        this.precio= pelota.getPrecio();
        this.stock= pelota.getStock();
        this.marca= pelota.getMarca();
        this.talle= pelota.getTalle();
        this.urlImg = pelota.getUrlImg();
    }

    public ProductoDTO(Short aShort){
        this.id = aShort.getId();
        this.name = aShort.getName();
        this.nombreProducto = aShort.getNombreProducto();
        this.costo= aShort.getCosto();
        this.precio= aShort.getPrecio();
        this.stock= aShort.getStock();
        this.marca= aShort.getMarca();
        this.talle= aShort.getTalle();
        this.urlImg = aShort.getUrlImg();
        this.equipo = aShort.getEquipo();
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
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
