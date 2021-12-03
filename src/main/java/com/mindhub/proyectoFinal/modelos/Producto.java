package com.mindhub.proyectoFinal.modelos;

import org.hibernate.annotations.DiscriminatorOptions;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "prod_tipo")
@DiscriminatorOptions(force = true)
@Table(name = "productos")
public abstract class Producto{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private double costo, precio;
    private int stock;
    private String marca, talle;

    public Producto() {
    }

    public Producto(double costo, double precio, int stock, String marca, String talle) {
        this.costo = costo;
        this.precio = precio;
        this.stock = stock;
        this.marca = marca;
        this.talle = talle;
    }

    public Long getId() {
        return id;
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

    public String getTalle() {
        return talle;
    }

    public void setTalle(String talle) {
        this.talle = talle;
    }
}
