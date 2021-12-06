package com.mindhub.proyectoFinal.dtos;

public class AplicarProductoDTO {
    private String name;
    private double precioCosto;
    private int stock;
    private String marca;
    private String[] talle;
    private String url;
    //Camiseta y short
    private String equipo;
    //botin
    private String tipo;

    public AplicarProductoDTO() {
    }

    public AplicarProductoDTO(String name, double precioCosto, int stock, String marca, String[] talle, String equipo, String tipo,String url) {
        this.name = name;
        this.precioCosto = precioCosto;
        this.stock = stock;
        this.marca = marca;
        this.talle = talle;
        this.url=url;
        this.equipo = equipo;
        this.tipo = tipo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(double precioCosto) {
        this.precioCosto = precioCosto;
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
