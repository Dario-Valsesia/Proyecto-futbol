package com.mindhub.proyectoFinal;

import com.mindhub.proyectoFinal.modelos.*;
import com.mindhub.proyectoFinal.modelos.Short;
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
	public CommandLineRunner initData(RepositorioCliente repositorioCliente, RepositorioCancha repositorioCancha, RepositorioReserva repositorioReserva, RepositorioProducto repositorioProducto, RepositorioProductoCliente repositorioProductoCliente){
		return (args) -> {
			Cliente cliente1 = new Cliente("Dario","Valsesia","dariovalsesia14@gmail.com", passwordEncoder.encode("123"));
			repositorioCliente.save(cliente1);
			Cancha cancha1 = new Cancha(22,4400D);
			repositorioCancha.save(cancha1);
			Reserva reserva1 = new Reserva(LocalDateTime.now(),LocalDateTime.now().plusHours(1),false,cliente1,cancha1);
			repositorioReserva.save(reserva1);


			Producto camiseta1 = new Camiseta("Camiseta","Camiseta de Boca", 2500, 4000, 30, "Kappa", new String[]{"XL","L"},"https://www.oscarbraessasdeportes.com.ar/wp-content/uploads/2020/07/WhatsApp-Image-2020-06-30-at-18.51.57.jpeg","Belgrano");
			repositorioProducto.save(camiseta1);
			Producto botin1 = new Botin("Botin","Botin dorado",2100, 4300, 15, "Lotto", new String[]{"40","42","44"},"https://www.oscarbraessasdeportes.com.ar/wp-content/uploads/2020/07/WhatsApp-Image-2020-06-30-at-18.51.57.jpeg", "Futbol 5");
			repositorioProducto.save(botin1);
			Producto medias1 =  new Medias("Medias","Medias con elastico reforzado",400, 1200, 75, "Nike", new String[]{"38","40","44"},"https://www.oscarbraessasdeportes.com.ar/wp-content/uploads/2020/07/WhatsApp-Image-2020-06-30-at-18.51.57.jpeg");
			repositorioProducto.save(medias1);
			Producto pelota1 = new Pelota("Pelota","Pelota con cuero reforzado",1500,2000,14,"Nike",new String[]{"1","2","3"},"https://redsport.vteximg.com.br/arquivos/ids/939668-2000-2000/GA025330771.jpg?v=636967276871130000");
			repositorioProducto.save(pelota1);
			Producto short1 = new Short("Short","Short deportivo",2000,2500,8,"Adidas",new String[]{"M","L","XL"},"https://static.dafiti.com.ar/p/adidas-5138-198794-1-product.jpg","Boca");
			repositorioProducto.save(short1);
			ProductoCliente productoCliente = new ProductoCliente(LocalDateTime.now(),"42",cliente1,botin1);
			repositorioProductoCliente.save(productoCliente);
		};
	}

}
