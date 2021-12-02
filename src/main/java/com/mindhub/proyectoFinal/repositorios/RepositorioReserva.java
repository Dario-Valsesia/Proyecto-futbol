package com.mindhub.proyectoFinal.repositorios;

import com.mindhub.proyectoFinal.modelos.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioReserva extends JpaRepository<Reserva,Long> {
}
