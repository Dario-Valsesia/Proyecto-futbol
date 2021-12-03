package com.mindhub.proyectoFinal.repositorios;

import com.mindhub.proyectoFinal.modelos.Cancha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RepositorioCancha extends JpaRepository<Cancha,Long> {
}
