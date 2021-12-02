package com.mindhub.proyectoFinal.dtos;


import com.mindhub.proyectoFinal.modelos.Reserva;

import java.time.LocalDateTime;

public class ReservaDTO {
    private long id;
    private LocalDateTime horaIngreso;
    private LocalDateTime horaSalida;
    private Boolean luces;

    public ReservaDTO() {
    }

    public ReservaDTO(Reserva reserva) {
        this.id = reserva.getId();
        this.horaIngreso = reserva.getHoraIngreso();
        this.horaSalida = reserva.getHoraSalida();
        this.luces = reserva.getLuces();
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
}
