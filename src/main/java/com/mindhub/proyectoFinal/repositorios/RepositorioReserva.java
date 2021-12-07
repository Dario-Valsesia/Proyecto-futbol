package com.mindhub.proyectoFinal.repositorios;

import com.mindhub.proyectoFinal.modelos.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource
public interface RepositorioReserva extends JpaRepository<Reserva,Long> {
    List<Reserva> findByHoraIngresoBetween(LocalDateTime inicio, LocalDateTime fin);
}
