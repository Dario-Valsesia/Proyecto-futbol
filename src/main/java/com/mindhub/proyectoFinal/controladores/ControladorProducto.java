package com.mindhub.proyectoFinal.controladores;


import com.mindhub.proyectoFinal.dtos.AplicarProductoDTO;
import com.mindhub.proyectoFinal.dtos.ProductoDTO;
import com.mindhub.proyectoFinal.modelos.Botin;
import com.mindhub.proyectoFinal.modelos.Camiseta;
import com.mindhub.proyectoFinal.modelos.Medias;
import com.mindhub.proyectoFinal.modelos.Producto;
import com.mindhub.proyectoFinal.repositorios.RepositorioCliente;
import com.mindhub.proyectoFinal.repositorios.RepositorioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("productos")
    public ResponseEntity<Object> agregarProducto(AplicarProductoDTO aplicarProductoDTO){
        String name = aplicarProductoDTO.getName();
        double precioCosto = aplicarProductoDTO.getPrecioCosto();
        int stock = aplicarProductoDTO.getStock();
        String marca = aplicarProductoDTO.getMarca();
        String[] talles = aplicarProductoDTO.getTalle();
        String equipo = aplicarProductoDTO.getEquipo();
        String tipo = aplicarProductoDTO.getTipo();
        String url = aplicarProductoDTO.getUrl();
        if (name.equals("Botin")){
            Producto producto = new Botin(name,precioCosto,precioCosto+precioCosto*0.3,stock,marca,talles,url,tipo);
            repositorioProducto.save(producto);
        }
        if(name.equals("Camiseta")){
            Producto producto = new Camiseta(name,precioCosto,precioCosto+precioCosto*0.3,stock,marca,talles,url,equipo);
            repositorioProducto.save(producto);
        }
        if (name.equals("Media")){
            Producto producto = new Medias(name,precioCosto,precioCosto+precioCosto*0.3,stock,marca,talles,url);
            repositorioProducto.save(producto);
        }
        return new ResponseEntity<>("creado", HttpStatus.OK);

    }
}
