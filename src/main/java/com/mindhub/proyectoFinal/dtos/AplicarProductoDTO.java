package com.mindhub.proyectoFinal.dtos;

public class AplicarProductoDTO {
    private String name;
    private String nombreProducto;
    private double precioCosto;
    private Integer porcentajeGanancia;
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

    public AplicarProductoDTO(String name,String nombreProducto, double precioCosto,Integer porcentajeGanancia, int stock, String marca, String[] talle, String equipo, String tipo,String url) {
        this.name = name;
        this.nombreProducto= nombreProducto;
        this.precioCosto = precioCosto;
        this.porcentajeGanancia = porcentajeGanancia;
        this.stock = stock;
        this.marca = marca;
        this.talle = talle;
        this.url = url;
        this.equipo = equipo;
        this.tipo = tipo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getPorcentajeGanancia() {
        return porcentajeGanancia;
    }

    public void setPorcentajeGanancia(Integer porcentajeGanancia) {
        this.porcentajeGanancia = porcentajeGanancia;
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
