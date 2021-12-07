package com.mindhub.proyectoFinal.controladores;

import com.mindhub.proyectoFinal.dtos.CanchaDTO;
import com.mindhub.proyectoFinal.dtos.ReservaDTO;
import com.mindhub.proyectoFinal.modelos.Cancha;
import com.mindhub.proyectoFinal.modelos.Cliente;
import com.mindhub.proyectoFinal.modelos.Reserva;
import com.mindhub.proyectoFinal.repositorios.RepositorioCancha;
import com.mindhub.proyectoFinal.repositorios.RepositorioCliente;
import com.mindhub.proyectoFinal.repositorios.RepositorioReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ControladorCancha {
    @Autowired
    RepositorioCancha repositorioCancha;
    @Autowired
    RepositorioReserva repositorioReserva;
    @Autowired
    RepositorioCliente repositorioCliente;

    @GetMapping("/canchas")
    public List<CanchaDTO> verCanchas(){
        return repositorioCancha.findAll().stream().map(cancha -> new CanchaDTO(cancha)).collect(Collectors.toList());
    }

    @GetMapping("/reservas")
    public List<ReservaDTO> buscarReservas(@RequestParam Long id,@RequestParam Integer[] integers){
       //Cancha cancha = repositorioCancha.findById(id).orElse(null);
        LocalDateTime localDateTime = LocalDateTime.of(integers[0],integers[1],integers[2],00,00);
        LocalDateTime localDateTime1 = LocalDateTime.of(integers[0],integers[1],integers[2],23,59);
        List<Reserva> reservas = repositorioReserva.findByHoraIngresoBetween(localDateTime,localDateTime1);
        reservas = reservas.stream().filter(reserva -> reserva.getCancha().getId()==id).collect(Collectors.toList());
        return reservas.stream().map(reserva -> new ReservaDTO(reserva)).collect(Collectors.toList());
    }

    @PostMapping("/reservar")
    public ResponseEntity<Object> crearReserva(@RequestParam String fechaHora,Long id, Authentication authentication){
        Cliente cliente = repositorioCliente.findByEmail(authentication.getName());
        Cancha cancha = repositorioCancha.findById(id).orElse(null);
        LocalDateTime fecha = LocalDateTime.parse(fechaHora);
        LocalDateTime fechaFin = fecha.plusHours(1);
        Boolean luces = false;
        if (fechaHora.isEmpty()){
            return new ResponseEntity<>("Seleccionar horario",HttpStatus.FORBIDDEN);
        }
        if(fecha.getHour()<17){
            return new ResponseEntity<>("No se puede reservar antes de las 17horas",HttpStatus.FORBIDDEN);
        }
        if(cancha.getReservas().stream().filter(reserva -> reserva.getHoraIngreso().isEqual(fecha)).collect(Collectors.toList()).size()>0){
            return new ResponseEntity<>("Esta cancha ya esta reservada",HttpStatus.FORBIDDEN);
        }
        if(fecha.getHour()>=19){
            luces = true;
        }
        Reserva reserva = new Reserva(fecha,fechaFin,luces,cliente,cancha);
        repositorioReserva.save(reserva);
        return new ResponseEntity<>("Cancha reservada", HttpStatus.CREATED);
    }

}