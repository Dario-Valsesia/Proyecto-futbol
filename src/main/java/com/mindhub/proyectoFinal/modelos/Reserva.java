package com.mindhub.proyectoFinal.modelos;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private LocalDateTime horaIngreso;
    private LocalDateTime horaSalida;
    private Boolean luces;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cliente_id")
    Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cancha_id")
    Cancha cancha;

    public Reserva() {
    }

    public Reserva(LocalDateTime horaIngreso, LocalDateTime horaSalida, Boolean luces, Cliente cliente, Cancha cancha) {
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
        this.luces = luces;
        this.cliente = cliente;
        this.cancha = cancha;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cancha getCancha() {
        return cancha;
    }

    public void setCancha(Cancha cancha) {
        this.cancha = cancha;
    }
}
