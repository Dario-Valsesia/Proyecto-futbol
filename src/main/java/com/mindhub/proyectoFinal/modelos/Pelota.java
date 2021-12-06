package com.mindhub.proyectoFinal.modelos;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pelota")
public class Pelota extends Producto{

    public Pelota() {
    }

    public Pelota(String name, double costo, double precio, int stock, String marca, String[] talle,String url) {
        super(name, costo, precio, stock, marca, talle,url);
    }
}
