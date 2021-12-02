package com.mindhub.proyectoFinal.modelos;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cancha {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private Integer cantidadJugadores;
    private Double precio;

    @OneToMany(mappedBy="cancha", fetch=FetchType.EAGER)
    private Set<Reserva> reservas = new HashSet<>();

    public Cancha() {
    }

    public Cancha(Integer cantidadJugadores, Double precio) {
        this.cantidadJugadores = cantidadJugadores;
        this.precio = precio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getCantidadJugadores() {
        return cantidadJugadores;
    }

    public void setCantidadJugadores(Integer cantidadJugadores) {
        this.cantidadJugadores = cantidadJugadores;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }
}
