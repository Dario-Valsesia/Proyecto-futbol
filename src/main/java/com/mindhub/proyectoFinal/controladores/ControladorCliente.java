package com.mindhub.proyectoFinal.controladores;

import com.mindhub.proyectoFinal.dtos.ClienteDTO;
import com.mindhub.proyectoFinal.modelos.Cliente;
import com.mindhub.proyectoFinal.repositorios.RepositorioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ControladorCliente {
    @Autowired
    RepositorioCliente repositorioCliente;
    @GetMapping("/clientes")
    public List<ClienteDTO> obtenerClientes(){
        return repositorioCliente.findAll().stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
    }
    @GetMapping("/cliente/actual")
    public ClienteDTO obtenerCliente(Authentication authentication){
        return new ClienteDTO(repositorioCliente.findByEmail(authentication.getName()));
    }
}
