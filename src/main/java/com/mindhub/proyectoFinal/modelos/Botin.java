package com.mindhub.proyectoFinal.modelos;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Botin")
public class Botin extends Producto implements Serializable {

    private String tipo;

    public Botin() {
    }

    public Botin(double costo, double precio, int stock, String marca, String talle, String tipo) {
        super(costo, precio, stock, marca, talle);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
