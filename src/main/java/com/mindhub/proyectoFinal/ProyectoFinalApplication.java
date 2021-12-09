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

			Cancha cancha1 = new Cancha(10,2000D,"Futbol 5");
			repositorioCancha.save(cancha1);
			Cancha cancha2 = new Cancha(14,2800D,"Futbol 7");
			repositorioCancha.save(cancha2);
			Cancha cancha3 = new Cancha(18,3600D,"Futbol 9");
			repositorioCancha.save(cancha3);
			Cancha cancha4 = new Cancha(22,4400D,"Futbol 11");
			repositorioCancha.save(cancha4);
			Reserva reserva1 = new Reserva(LocalDateTime.of(2021,12,7,18,00),LocalDateTime.of(2021,12,7,19,00),false,cliente1,cancha1);
			repositorioReserva.save(reserva1);
			Reserva reserva2 = new Reserva(LocalDateTime.of(2021,12,7,19,00),LocalDateTime.of(2021,12,7,20,00),false,cliente1,cancha2);
			repositorioReserva.save(reserva2);
			Reserva reserva3 = new Reserva(LocalDateTime.of(2021,12,8,16,00),LocalDateTime.of(2021,12,8,17,00),false,cliente1,cancha1);
			repositorioReserva.save(reserva3);
			Reserva reserva4 = new Reserva(LocalDateTime.of(2021,12,7,19,00),LocalDateTime.of(2021,12,7,20,00),false,cliente1,cancha1);
			repositorioReserva.save(reserva4);

			Producto camiseta0 = new Camiseta("Camiseta","Camiseta de Boca", 2500, 4000, 30, "Kappa", new String[]{"XL","L"},"https://www.oscarbraessasdeportes.com.ar/wp-content/uploads/2020/07/WhatsApp-Image-2020-06-30-at-18.51.57.jpeg","Boca");
			repositorioProducto.save(camiseta0);
			Producto camiseta1 = new Camiseta("camiseta liverpool", "Camiseta Liverpool", 1500, 3000, 10, "Nike", new String[]{"S","M","L","XL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898137/pngwing.com_21_d8f911.png", "Liverpool");
			repositorioProducto.save(camiseta1);
			Producto camiseta2 = new Camiseta("camiseta Seleccion Argentina", "Camiseta Seleccion Argentina", 1800, 3500, 20, "Adidas", new String[]{"S","M","L","XL","XXL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898138/pngwing.com_23_bi4t9w.png", "Adidas");
			repositorioProducto.save(camiseta2);
			Producto camiseta3 = new Camiseta("camiseta Chelsea", "Camiseta Chelsea", 1400, 2800, 5, "Adidas", new String[]{"M","L","XL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898138/pngwing.com_22_ixjszy.png", "Adidas");
			repositorioProducto.save(camiseta3);
			Producto camiseta4 = new Camiseta("camiseta Brasi", "Camiseta Brasil", 1600, 3000, 10, "Nike", new String[]{"M","L","XL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898138/pngwing.com_26_zilbxm.png", "Nike");
			repositorioProducto.save(camiseta4);
			Producto camiseta5 = new Camiseta("camiseta Alemania", "Camiseta Alemania", 1400, 2800, 5, "Adidas", new String[]{"S","M","L","XL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898138/pngwing.com_27_ibv41x.png", "Adidas");
			repositorioProducto.save(camiseta5);
			Producto camiseta6 = new Camiseta("camiseta Arquero", "Camiseta Arquero", 1200, 2400, 20, "Nike", new String[]{"M","L","XL","XXL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898139/pngwing.com_29_cnqqf9.png", "Nike");
			repositorioProducto.save(camiseta6);
			Producto camiseta7 = new Camiseta("camiseta Arquero Manga Corta", "Camiseta Arquero Manga Corta", 1000, 2000, 15, "Veto", new String[]{"S","M","L","XL","XXL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898139/pngwing.com_25_o0ncdu.png", "Veto");
			repositorioProducto.save(camiseta7);
			Producto camiseta8 = new Camiseta("camiseta Esquipo Chino", "Camiseta Futbol Chino", 900, 1800, 30, "Nike", new String[]{"S","M","L","XL","XXL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898139/pngwing.com_28_dnzy0h.png", "Nike");
			repositorioProducto.save(camiseta8);
			Producto camiseta9 = new Camiseta("camiseta Equipo Chino", "Camiseta Futbol Chino", 900, 1800, 30, "Nike", new String[]{"S","M","L","XL","XXL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898139/pngwing.com_28_dnzy0h.png", "Nike");
			repositorioProducto.save(camiseta9);
			Producto camiseta10 = new Camiseta("camiseta Real Madrid", "Camiseta Real Madrid", 1700, 3400, 5, "Adidas", new String[]{"S","M","L","XL","XXL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898139/pngwing.com_20_jw3sb1.png", "Adidas");
			repositorioProducto.save(camiseta10);


			Producto botin0 = new Botin("Botin","Botin dorado",2100, 4300, 15, "Lotto", new String[]{"40","42","44"},"https://www.oscarbraessasdeportes.com.ar/wp-content/uploads/2020/07/WhatsApp-Image-2020-06-30-at-18.51.57.jpeg", "Fútbol 5");
			repositorioProducto.save(botin0);
			Producto botin1 = new Botin("Botin","Botin Azul",2500, 5000, 10, "Adidas", new String[]{"38","40","42","44"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638897293/pngwing.com_16_rlhinn.png", "Futbol 11");
			repositorioProducto.save(botin1);
			Producto botin2 = new Botin("Botin","Botin Mercurial",3000, 6000, 8, "Nike", new String[]{"38","40","42","44", "46"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638897294/pngwing.com_15_tbmr6p.png", "Futbol 11");
			repositorioProducto.save(botin2);
			Producto botin3 = new Botin("Botin","Botin Tipo Bota",3500, 7000, 5, "Adidas", new String[]{"38","40","42","44"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638897294/pngwing.com_14_zq3ozz.png", "Futbol 11");
			repositorioProducto.save(botin3);
			Producto botin4 = new Botin("Botin","Botin",1500, 3000, 20, "Joguel", new String[]{"38","40","42","44"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638897295/pngwing.com_10_czybop.png", "Futbol 11");
			repositorioProducto.save(botin4);
			Producto botin5 = new Botin("Botin","Botin p/niño",1000, 2000, 20, "Bastian", new String[]{"34","36","38","40"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638897295/pngwing.com_18_pl4znh.png", "Futbol 11");
			repositorioProducto.save(botin5);
			Producto botin6 = new Botin("Botin","Botin tipo Bota",1500, 3000, 10, "Nike", new String[]{"38","40","42","44"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1639060190/descarga_1_ylwcf9.jpg", "Futbol 5");
			repositorioProducto.save(botin6);
			Producto botin7 = new Botin("Botin","Botin Blanco/Azul",1200, 2400, 12, "Adidas", new String[]{"40","42","44"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1639060190/images_wwb6wx.jpg", "Futbol 5");
			repositorioProducto.save(botin7);
			Producto botin8 = new Botin("Botin","Botin Blanco/Naranja",1400, 2800, 15, "Nike", new String[]{"40","42","44","46"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1639060190/descarga_3_ixqaay.jpg", "Futbol 5");
			repositorioProducto.save(botin8);
			Producto botin9 = new Botin("Botin","Botin Negro/Naranja",1000, 2000, 20, "Umbro", new String[]{"40","42","44","46"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1639060190/descarga_2_rtn6dx.jpg", "Futbol 5");
			repositorioProducto.save(botin9);
			Producto botin10 = new Botin("Botin","Botin Bota Larga",2000, 4000, 5, "Adidas", new String[]{"40","42","44","46"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1639060191/images_1_stgi3o.jpg", "Futbol 5");
			repositorioProducto.save(botin10);
			Producto botin11 = new Botin("Botin","Botin Negro",1800, 3600, 10, "Puma", new String[]{"40","42","44","46"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1639060191/descarga_4_xuh9us.jpg", "Futbol 5");
			repositorioProducto.save(botin11);

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
