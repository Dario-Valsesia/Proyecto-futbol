package com.mindhub.proyectoFinal.repositorios;

import com.mindhub.proyectoFinal.modelos.Medias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RepositorioMedias extends JpaRepository<Medias, Long> {
}
