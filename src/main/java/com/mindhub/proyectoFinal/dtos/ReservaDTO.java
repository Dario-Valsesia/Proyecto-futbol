package com.mindhub.proyectoFinal.dtos;


import com.mindhub.proyectoFinal.modelos.Reserva;

import java.time.LocalDateTime;

public class ReservaDTO {
    private long id;
    private LocalDateTime horaIngreso;
    private LocalDateTime horaSalida;
    private Boolean luces;
    private String nombreCancha;
    private Double precio;

    public ReservaDTO() {
    }

    public ReservaDTO(Reserva reserva) {
        this.id = reserva.getId();
        this.horaIngreso = reserva.getHoraIngreso();
        this.horaSalida = reserva.getHoraSalida();
        this.luces = reserva.getLuces();
        this.nombreCancha = reserva.getCancha().getNombre();
        this.precio = reserva.getCancha().getPrecio();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(LocalDateTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Boolean getLuces() {
        return luces;
    }

    public void setLuces(Boolean luces) {
        this.luces = luces;
    }

    public String getNombreCancha() {return nombreCancha;}

    public void setNombreCancha(String nombreCancha) {this.nombreCancha = nombreCancha;}

    public Double getPrecio() {return precio;}

    public void setPrecio(Double precio) {this.precio = precio;}
}
