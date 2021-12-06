package com.mindhub.proyectoFinal.repositorios;


import com.mindhub.proyectoFinal.modelos.Producto;
import com.mindhub.proyectoFinal.modelos.ProductoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RepositorioProductoCliente  extends JpaRepository<ProductoCliente, Long> {
}
