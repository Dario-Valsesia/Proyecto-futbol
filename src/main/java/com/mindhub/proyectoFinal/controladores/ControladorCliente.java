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
import java.util.Objects;
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
            return new ResponseEntity<>("Algunos campos no pueden ir vacíos.", HttpStatus.FORBIDDEN);
        }

        if(repositorioCliente.findByEmail(email) != null){
            return new ResponseEntity<>("Correo electrónico en uso. Por favor, intente con uno diferente.", HttpStatus.FORBIDDEN);
        }
        if(contraseña.length() < 8 || contraseña.length() > 12){
            return new ResponseEntity<>("La contraseña debe contener entre 8 y 12 caracteres", HttpStatus.FORBIDDEN);
        }

        repositorioCliente.save(new Cliente(nombre, apellido, email, passwordEncoder.encode(contraseña)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/clientes/olvido-contraseña")
    public ResponseEntity<Object> cambioContraseña(@RequestParam String email,
                                                   @RequestParam String contraseña){

        Cliente cliente = repositorioCliente.findByEmail(email);

        if(cliente == null){
            return new ResponseEntity<>("Usuario no encontrado.", HttpStatus.FORBIDDEN);
        }

        if(contraseña.length() < 8 || contraseña.length() > 12){
            return new ResponseEntity<>("La contraseña debe contener entre 8 y 12 caracteres", HttpStatus.FORBIDDEN);
        }

        cliente.setPassword(passwordEncoder.encode(contraseña));

        repositorioCliente.save(cliente);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*@GetMapping("/cliente/actual/{id}")
    public ClienteDTO getCliente(@PathVariable Long id){
        return repositorioCliente.findById(id).map(cliente -> new ClienteDTO(cliente)).orElse(null);
    }*/

    @PutMapping("/cliente/actual/personal")
    public ResponseEntity<Object> cambioDatos(
            @RequestParam String firstName,
            @RequestParam String lastName,
            Authentication authentication){

        Cliente signCliente = repositorioCliente.findByEmail(authentication.getName());

        signCliente.setFirstName(firstName);
        signCliente.setLastName(lastName);

        repositorioCliente.save(signCliente);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/cliente/actual/password")
    public ResponseEntity<Object> cambioContraseña(
            @RequestParam String password,
            Authentication authentication){

        Cliente signCliente = repositorioCliente.findByEmail(authentication.getName());

        if(password.length() < 8 || password.length() > 12){
            return new ResponseEntity<>("La contraseña debe contener entre 8 y 12 caracteres", HttpStatus.FORBIDDEN);
        }

        signCliente.setPassword(passwordEncoder.encode(password));

        repositorioCliente.save(signCliente);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
