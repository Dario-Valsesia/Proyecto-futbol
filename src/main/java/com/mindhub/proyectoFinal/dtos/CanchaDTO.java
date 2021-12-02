package com.mindhub.proyectoFinal.dtos;

import com.mindhub.proyectoFinal.modelos.Cancha;
import com.mindhub.proyectoFinal.modelos.Reserva;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CanchaDTO {
    private long id;
    private Integer cantidadJugadores;
    private Double precio;
    private Set<ReservaDTO> reservas = new HashSet<>();

    public CanchaDTO() {
    }

    public CanchaDTO(Cancha cancha) {
        this.id = cancha.getId();
        this.cantidadJugadores = cancha.getCantidadJugadores();
        this.precio = cancha.getPrecio();
        this.reservas = cancha.getReservas().stream().map(reserva -> new ReservaDTO(reserva)).collect(Collectors.toSet());
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

    public Set<ReservaDTO> getReservas() {
        return reservas;
    }

    public void setReservas(Set<ReservaDTO> reservas) {
        this.reservas = reservas;
    }
}
