package com.mindhub.proyectoFinal.controladores;


import com.mindhub.proyectoFinal.dtos.AplicarProductoDTO;
import com.mindhub.proyectoFinal.dtos.ProductoClienteDTO;
import com.mindhub.proyectoFinal.dtos.ProductoDTO;
import com.mindhub.proyectoFinal.modelos.*;
import com.mindhub.proyectoFinal.modelos.Short;
import com.mindhub.proyectoFinal.repositorios.RepositorioCliente;
import com.mindhub.proyectoFinal.repositorios.RepositorioProducto;
import com.mindhub.proyectoFinal.repositorios.RepositorioProductoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    @Autowired
    RepositorioProductoCliente repositorioProductoCliente;

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
            } else if (producto instanceof Medias){
                Medias medias = (Medias) producto;
                listaProductosDTO.add(new ProductoDTO(medias));
            } else if(producto instanceof Short){
                Short aShort = (Short) producto;
                listaProductosDTO.add(new ProductoDTO(aShort));
            } else{
                Pelota pelota =(Pelota) producto;
                listaProductosDTO.add(new ProductoDTO(pelota));
            }
        });
        return listaProductosDTO;
    }

    @PostMapping("/productos")
    public ResponseEntity<Object> agregarProducto(@RequestBody AplicarProductoDTO aplicarProductoDTO){

        String name = aplicarProductoDTO.getName();
        String nombreProducto = aplicarProductoDTO.getNombreProducto();
        double precioCosto = aplicarProductoDTO.getPrecioCosto();
        Integer porcentajeGanacia = aplicarProductoDTO.getPorcentajeGanancia();
        int stock = aplicarProductoDTO.getStock();
        String marca = aplicarProductoDTO.getMarca();
        String[] talles = aplicarProductoDTO.getTalle();
        String equipo = aplicarProductoDTO.getEquipo();
        String tipo = aplicarProductoDTO.getTipo();
        String url = aplicarProductoDTO.getUrl();

        if(name.isEmpty() || nombreProducto.isEmpty() || precioCosto < 0 || porcentajeGanacia < 0 || stock < 0 ||
                marca.isEmpty() || talles.length==0 || url.isEmpty()){
            return new ResponseEntity<>("Data vacia", HttpStatus.FORBIDDEN);
        }
        if (name.equals("Botin")){
            Producto producto = new Botin(name,nombreProducto,precioCosto,precioCosto+((porcentajeGanacia*precioCosto)/100),stock,marca,talles,url,tipo);
            repositorioProducto.save(producto);
        }
        if(name.equals("Camiseta")){
            Producto producto = new Camiseta(name,nombreProducto,precioCosto,precioCosto+((porcentajeGanacia*precioCosto)/100),stock,marca,talles,url,equipo);
            repositorioProducto.save(producto);
        }
        if (name.equals("Media")){
            Producto producto = new Medias(name,nombreProducto,precioCosto,precioCosto+((porcentajeGanacia*precioCosto)/100),stock,marca,talles,url);
            repositorioProducto.save(producto);
        }
        if(name.equals("Pelota")){
            Producto producto = new Pelota(name,nombreProducto,precioCosto,precioCosto+((porcentajeGanacia*precioCosto)/100),stock,marca,talles,url);
            repositorioProducto.save(producto);
        }
        if(name.equals("Short")){
            Producto producto = new Short(name,nombreProducto,precioCosto,precioCosto+((porcentajeGanacia*precioCosto)/100),stock,marca,talles,url,equipo);
            repositorioProducto.save(producto);
        }
        return new ResponseEntity<>("Éxito al registrar producto", HttpStatus.OK);
    }

    @PostMapping("/comprar/producto")
    public ResponseEntity<Object> comprarProducto(@RequestParam Long id, @RequestParam String talle , Authentication authentication){

        Cliente cliente = repositorioCliente.findByEmail(authentication.getName());

        Producto producto = repositorioProducto.findById(id).orElse(null);


        ProductoCliente productoCliente = new ProductoCliente(LocalDateTime.now(),talle,cliente,producto);
        repositorioProductoCliente.save(productoCliente);

        return new ResponseEntity<>("Compra realizada con exitos", HttpStatus.CREATED);
    }

}
