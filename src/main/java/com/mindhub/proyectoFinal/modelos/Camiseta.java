package com.mindhub.proyectoFinal.modelos;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Camiseta")
public class Camiseta extends Producto implements Serializable {

    private String equipo;

    public Camiseta() {
    }

    public Camiseta(double costo, double precio, int stock, String marca, String talle, String equipo) {
        super(costo, precio, stock, marca, talle);
        this.equipo = equipo;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }
}
