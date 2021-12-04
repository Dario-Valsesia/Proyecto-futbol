package com.mindhub.proyectoFinal.modelos;

import javax.persistence.*;

@Entity
@Table(name = "camiseta")
public class Camiseta extends Producto{

    private Long id;
    private String equipo;

    public Camiseta() {
    }

    public Camiseta(String name, double costo, double precio, int stock, String marca, String talle, String equipo) {
        super(name, costo, precio, stock, marca, talle);
        this.equipo = equipo;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }
}
