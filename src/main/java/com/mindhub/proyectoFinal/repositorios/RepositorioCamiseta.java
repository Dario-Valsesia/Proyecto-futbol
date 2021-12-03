package com.mindhub.proyectoFinal.repositorios;

import com.mindhub.proyectoFinal.modelos.Camiseta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioCamiseta extends JpaRepository<Camiseta, Long> {
}
