package com.mindhub.proyectoFinal.controladores;


import com.mindhub.proyectoFinal.dtos.ProductoDTO;
import com.mindhub.proyectoFinal.modelos.Botin;
import com.mindhub.proyectoFinal.modelos.Camiseta;
import com.mindhub.proyectoFinal.modelos.Medias;
import com.mindhub.proyectoFinal.modelos.Producto;
import com.mindhub.proyectoFinal.repositorios.RepositorioCliente;
import com.mindhub.proyectoFinal.repositorios.RepositorioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ControladorProducto {
    @Autowired
    RepositorioCliente repositorioCliente;
    @Autowired
    RepositorioProducto repositorioProducto;

    @GetMapping("/productos")
    public List<ProductoDTO> obtenerProductor() {
        List<Producto> listaProducto = repositorioProducto.findAll();
        List<ProductoDTO> listaProductosDTO = new ArrayList<>();
        listaProducto.forEach(producto -> {
            if (producto instanceof Camiseta) {
                Camiseta camiseta = (Camiseta) producto;
                listaProductosDTO.add(new ProductoDTO(camiseta));
            } else if (producto instanceof Botin) {
                Botin botin = (Botin) producto;
                listaProductosDTO.add(new ProductoDTO(botin));
            } else {
                Medias medias = (Medias) producto;
                listaProductosDTO.add(new ProductoDTO(medias));
            }
        });
        return listaProductosDTO;
    }
}
