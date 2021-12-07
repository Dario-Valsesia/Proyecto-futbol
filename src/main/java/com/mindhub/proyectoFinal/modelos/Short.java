package com.mindhub.proyectoFinal.modelos;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "short")
public class Short extends Producto{

    private String equipo;

    public Short() {
    }

    public Short(String name,String nombreProducto, double costo, double precio, int stock, String marca, String[] talle, String url,String equipo) {
        super(name, nombreProducto,costo, precio, stock, marca, talle,url);
        this.equipo = equipo;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }
}
