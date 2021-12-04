package com.mindhub.proyectoFinal;

import com.mindhub.proyectoFinal.modelos.*;
import com.mindhub.proyectoFinal.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
public class ProyectoFinalApplication {
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(RepositorioCliente repositorioCliente, RepositorioCancha repositorioCancha, RepositorioReserva repositorioReserva, RepositorioCamiseta repositorioCamiseta, RepositorioBotin repositorioBotin, RepositorioMedias repositorioMedias, RepositorioProducto repositorioProducto){
		return (args) -> {
			Cliente cliente1 = new Cliente("Dario","Valsesia","dariovalsesia14@gmail.com", passwordEncoder.encode("123"));
			repositorioCliente.save(cliente1);
			Cancha cancha1 = new Cancha(22,4400D);
			repositorioCancha.save(cancha1);
			Reserva reserva1 = new Reserva(LocalDateTime.now(),LocalDateTime.now().plusHours(1),false,cliente1,cancha1);
			repositorioReserva.save(reserva1);

			/*Camiseta camiseta1 = new Camiseta("Camiseta", 2500, 4000, 30, "Kappa", "XL", "Belgrano");
			repositorioCamiseta.save(camiseta1);
			Botin botin1 = new Botin("Botin",2100, 4300, 15, "Lotto", "42", "Futbol 5");
			repositorioBotin.save(botin1);
			Medias medias1 = new Medias("Medias",400, 1200, 75, "Nike", "42");
			repositorioMedias.save(medias1);*/
			Producto camiseta1 = new Camiseta("Camiseta", 2500, 4000, 30, "Kappa", new String[]{"XL,L"}, "Belgrano");
			repositorioProducto.save(camiseta1);
			Producto botin1 = new Botin("Botin",2100, 4300, 15, "Lotto", new String[]{"40","42","44"}, "Futbol 5");
			repositorioProducto.save(botin1);
			Producto medias1 =  new Medias("Medias",400, 1200, 75, "Nike", new String[]{"38","40","44"});
			repositorioProducto.save(medias1);
		};
	}

}
