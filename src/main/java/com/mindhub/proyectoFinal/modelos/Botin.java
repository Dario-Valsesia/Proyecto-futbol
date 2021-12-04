package com.mindhub.proyectoFinal.modelos;

import javax.persistence.*;

@Entity
@Table(name = "botin")
public class Botin extends Producto{

    private String tipo;

    public Botin() {
    }

    public Botin(String name, double costo, double precio, int stock, String marca, String talle, String tipo) {
        super(name, costo, precio, stock, marca, talle);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
