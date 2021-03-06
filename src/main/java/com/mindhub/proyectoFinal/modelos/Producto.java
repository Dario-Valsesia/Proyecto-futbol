package com.mindhub.proyectoFinal.modelos;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Producto{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(name = "name")
    private String name;
    private String nombreProducto;
    private double costo;
    private double precio;
    private int stock;
    private String marca;
    private String[] talle;
    private String urlImg;
    @OneToMany(mappedBy="producto", fetch=FetchType.EAGER)
    private Set<ProductoCliente> productosCliente = new HashSet<>();



    public Producto() {
    }

    public Producto(String name, String nombreProducto,double costo, double precio, int stock, String marca, String[] talle,String urlImg) {
        this.name = name;
        this.nombreProducto=nombreProducto;
        this.costo = costo;
        this.precio = precio;
        this.stock = stock;
        this.marca = marca;
        this.talle = talle;
        this.urlImg = urlImg;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Set<ProductoCliente> getProductosCliente() {
        return productosCliente;
    }

    public void setProductosCliente(Set<ProductoCliente> productosCliente) {
        this.productosCliente = productosCliente;
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public String[] getTalle() {
        return talle;
    }

    public void setTalle(String[] talle) {
        this.talle = talle;
    }
}
