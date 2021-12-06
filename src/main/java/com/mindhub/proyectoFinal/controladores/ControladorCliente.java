package com.mindhub.proyectoFinal.controladores;

import com.mindhub.proyectoFinal.dtos.ClienteDTO;
import com.mindhub.proyectoFinal.modelos.Cliente;
import com.mindhub.proyectoFinal.repositorios.RepositorioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ControladorCliente {

    @Autowired
    RepositorioCliente repositorioCliente;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/clientes")
    public List<ClienteDTO> obtenerClientes(){
        return repositorioCliente.findAll().stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
    }

    @GetMapping("/cliente/actual")
    public ClienteDTO obtenerCliente(Authentication authentication){
        return new ClienteDTO(repositorioCliente.findByEmail(authentication.getName()));
    }

    @PostMapping("/clientes")
    public ResponseEntity<Object> register(@RequestParam String nombre, @RequestParam String apellido,
                                           @RequestParam String email, @RequestParam String contraseña){
        if(nombre.isEmpty()||apellido.isEmpty()||email.isEmpty()||contraseña.isEmpty()){
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if(repositorioCliente.findByEmail(email) != null){
            return new ResponseEntity<>("Mail already in use", HttpStatus.FORBIDDEN);
        }
        repositorioCliente.save(new Cliente(nombre, apellido, email, passwordEncoder.encode(contraseña)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
