package com.mindhub.proyectoFinal.modelos;

import javax.persistence.*;

@Entity
@Table(name = "medias")
public class Medias extends Producto {

    public Medias() {
    }

    public Medias(String name, double costo, double precio, int stock, String marca, String talle) {
        super(name, costo, precio, stock, marca, talle);
    }
}
