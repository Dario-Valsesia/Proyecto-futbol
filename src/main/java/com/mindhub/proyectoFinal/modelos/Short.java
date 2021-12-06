package com.mindhub.proyectoFinal.modelos;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "short")
public class Short extends Producto{

    private String equipo;

    public Short() {
    }

    public Short(String name, double costo, double precio, int stock, String marca, String[] talle, String equipo) {
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