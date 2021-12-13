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
			Cliente cliente2 = new Cliente("Matias","Gomez","asdasd@gmail.com", passwordEncoder.encode("123"));
			repositorioCliente.save(cliente2);

			Cancha cancha1 = new Cancha(10,2000D,"Fútbol 5");
			repositorioCancha.save(cancha1);
			Cancha cancha2 = new Cancha(14,2800D,"Fútbol 7");
			repositorioCancha.save(cancha2);
			Cancha cancha3 = new Cancha(18,3600D,"Fútbol 9");
			repositorioCancha.save(cancha3);
			Cancha cancha4 = new Cancha(22,4400D,"Fútbol 11");
			repositorioCancha.save(cancha4);
			Reserva reserva1 = new Reserva(LocalDateTime.of(2021,12,7,18,00),LocalDateTime.of(2021,12,7,19,00),false,cliente1,cancha1);
			repositorioReserva.save(reserva1);
			Reserva reserva2 = new Reserva(LocalDateTime.of(2021,12,7,22,00),LocalDateTime.of(2021,12,7,23,00),false,cliente1,cancha2);
			repositorioReserva.save(reserva2);
			Reserva reserva3 = new Reserva(LocalDateTime.of(2021,12,8,17,00),LocalDateTime.of(2021,12,8,17,00),false,cliente1,cancha1);
			repositorioReserva.save(reserva3);
			Reserva reserva4 = new Reserva(LocalDateTime.of(2021,12,14,18,00),LocalDateTime.of(2021,12,14,19,00),false,cliente2,cancha1);
			repositorioReserva.save(reserva4);
			Reserva reserva5 = new Reserva(LocalDateTime.of(2021,12,14,22,00),LocalDateTime.of(2021,12,14,23,00),false,cliente2,cancha1);
			repositorioReserva.save(reserva5);


			Producto camiseta1 = new Camiseta("Camiseta", "Camiseta Liverpool", 1500, 3000, 10, "Nike", new String[]{"S","M","L","XL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898137/pngwing.com_21_d8f911.png", "Liverpool");
			repositorioProducto.save(camiseta1);
			Producto camiseta2 = new Camiseta("Camiseta", "Camiseta Argentina", 1800, 3500, 20, "Adidas", new String[]{"S","M","L","XL","XXL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898138/pngwing.com_23_bi4t9w.png", "Adidas");
			repositorioProducto.save(camiseta2);
			Producto camiseta3 = new Camiseta("Camiseta", "Camiseta Chelsea", 1400, 2800, 5, "Adidas", new String[]{"M","L","XL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898138/pngwing.com_22_ixjszy.png", "Adidas");
			repositorioProducto.save(camiseta3);
			Producto camiseta4 = new Camiseta("Camiseta", "Camiseta Brasil", 1600, 3000, 10, "Nike", new String[]{"M","L","XL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898138/pngwing.com_26_zilbxm.png", "Nike");
			repositorioProducto.save(camiseta4);
			Producto camiseta5 = new Camiseta("Camiseta", "Camiseta Alemania", 1400, 2800, 5, "Adidas", new String[]{"S","M","L","XL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898138/pngwing.com_27_ibv41x.png", "Adidas");
			repositorioProducto.save(camiseta5);
			Producto camiseta6 = new Camiseta("Camiseta", "Camiseta Arquero", 1200, 2400, 20, "Nike", new String[]{"M","L","XL","XXL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898139/pngwing.com_29_cnqqf9.png", "Nike");
			repositorioProducto.save(camiseta6);
			Producto camiseta7 = new Camiseta("Camiseta", "Camiseta Arquero Manga Corta", 1000, 2000, 15, "Veto", new String[]{"S","M","L","XL","XXL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898139/pngwing.com_25_o0ncdu.png", "Veto");
			repositorioProducto.save(camiseta7);
			Producto camiseta8 = new Camiseta("Camiseta", "Camiseta Futbol Chino", 900, 1800, 30, "Nike", new String[]{"S","M","L","XL","XXL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898139/pngwing.com_28_dnzy0h.png", "Nike");
			repositorioProducto.save(camiseta8);
			Producto camiseta10 = new Camiseta("Camiseta", "Camiseta Real Madrid", 1700, 3400, 5, "Adidas", new String[]{"S","M","L","XL","XXL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898139/pngwing.com_20_jw3sb1.png", null);
			repositorioProducto.save(camiseta10);

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

			Producto medias1 =  new Medias("Medias","Medias Blancas, franjas Azules",750, 1500, 100, "Nike", new String[]{"38","40","44"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898983/th_10_zbqpbr.jpg");
			repositorioProducto.save(medias1);
			Producto medias2 =  new Medias("Medias","Medias Blancas lisa",600, 1200, 120, "Topper", new String[]{"38","40","44"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898983/th_9_b0moiv.jpg");
			repositorioProducto.save(medias2);
			Producto medias3 =  new Medias("Medias","Medias Azul lisa",600, 1200, 120, "Adidas", new String[]{"38","40","44"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898984/medias-futbol-adidas-milano-azul_hy8ydl.jpg");
			repositorioProducto.save(medias3);
			Producto medias4 =  new Medias("Medias","Medias de Compresion",800, 1600, 120, "Nike", new String[]{"38","40","44"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898984/122601_lvzaz0.webp");
			repositorioProducto.save(medias4);
			Producto medias5 =  new Medias("Medias","Medias Reversibles",1000, 2000, 120, "Adidas", new String[]{"38","40","44"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898984/D_NQ_NP_676648-MLV32984547625_112019-O_vvlnc7.webp");
			repositorioProducto.save(medias5);
			Producto medias6 =  new Medias("Medias","Medias Bordo Lisa",600, 1200, 120, "Adidas", new String[]{"38","40","44"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898984/medias-adidas-milano-16-rojas-1-1560946685_bfcd3r.jpg");
			repositorioProducto.save(medias6);
			Producto medias7 =  new Medias("Medias","Medias Verde Lisa",500, 1000, 120, "Umbro", new String[]{"38","40","44"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898984/descarga_jyu11d.jpg");
			repositorioProducto.save(medias7);
			Producto medias8 =  new Medias("Medias","Medias Naranja Lisa",600, 1200, 120, "Adidas", new String[]{"38","40","44"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638898984/medias-de-futbol-para-nino-naranjas_kbe6r2.jpg");
			repositorioProducto.save(medias8);
			Producto medias9 =  new Medias("Medias","Medias Azul lisa con lineas",650, 1300, 120, "Pumas", new String[]{"38","40","44"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638899043/th_11_prwjid.jpg");
			repositorioProducto.save(medias9);
			Producto medias10 =  new Medias("Medias","Medias Rosadas lisa ",650, 1300, 120, "Women", new String[]{"38","40","44"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638899043/th_12_lkkgbi.jpg");
			repositorioProducto.save(medias10);

			Producto pelota1 = new Pelota("Pelota","Pelota Seleccion Argentina",1500,2000,14,"Adidas",new String[]{"3","4","5"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638896200/pngwing.com_9_hvttt1.png");
			repositorioProducto.save(pelota1);
			Producto pelota2 = new Pelota("Pelota","Pelota Mundial 2018",1800,3200,14,"Adidas",new String[]{"3","4","5"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638896201/pngwing.com_2_x6igxs.png");
			repositorioProducto.save(pelota2);
			Producto pelota3 = new Pelota("Pelota","Pelota Champions League",1800,3200,14,"Adidas",new String[]{"3","4","5"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638896201/pngwing.com_1_qnqjx7.png");
			repositorioProducto.save(pelota3);
			Producto pelota4 = new Pelota("Pelota","Pelota 2017",1500,3000,20,"Nike",new String[]{"3","4","5"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638896201/pngwing.com_fhkaen.png");
			repositorioProducto.save(pelota4);
			Producto pelota5 = new Pelota("Pelota","Pelota Paris Saint German",1800,3600,20,"Nike",new String[]{"3","4","5"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638896202/pngwing.com_5_newitg.png");
			repositorioProducto.save(pelota5);
			Producto pelota6 = new Pelota("Pelota","Pelota Futsal",1200,2400,20,"Futsal",new String[]{"1","2","3"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638896202/pngwing.com_7_mlj2cm.png");
			repositorioProducto.save(pelota6);
			Producto pelota7 = new Pelota("Pelota","Pelota Naranja",1200,2400,20,"Nike",new String[]{"1","2","3"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638896203/pngwing.com_4_pnr3vr.png");
			repositorioProducto.save(pelota7);
			Producto pelota8 = new Pelota("Pelota","Pelota Blanca",1200,2400,20,"Adidas",new String[]{"1","2","3"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638896203/pngwing.com_8_s3yf4q.png");
			repositorioProducto.save(pelota8);
			Producto pelota9 = new Pelota("Pelota","Pelota Barcelona",2000,4000,20,"Nike",new String[]{"3","4","5"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638896203/pngwing.com_6_a8aynj.png");
			repositorioProducto.save(pelota9);


			Producto short1 = new Short("Short","Short Verde",2000,2500,8,"New Balance",new String[]{"M","L","XL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638899745/th_3_zmewye.jpg","Palmeiras");
			repositorioProducto.save(short1);
			Producto short2 = new Short("Short","Short Real Madrid Negro",3000,4000,10,"Adidas",new String[]{"M","L","XL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638899745/th_xgpg8c.jpg","Real Madrid");
			repositorioProducto.save(short2);
			Producto short3 = new Short("Short","Short Barcelona Rojo",3000,4000,10,"Nike",new String[]{"M","L","XL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638899745/th_2_jqttax.jpg","Barcelona");
			repositorioProducto.save(short3);
			Producto short4 = new Short("Short","Short Negro",1500,3000,10,"Futsal",new String[]{"M","L","XL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638899745/th_8_mdl8sx.jpg","Futsal");
			repositorioProducto.save(short4);
			Producto short5 = new Short("Short","Short Valencia",2000,3000,10,"Hummel",new String[]{"M","L","XL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638899745/th_1_krf2wk.jpg","Valencia");
			repositorioProducto.save(short5);
			Producto short6 = new Short("Short","Short Barcelona Azul",3000,4000,10,"Nike",new String[]{"M","L","XL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638899745/th_7_b21zii.jpg","Barcelona");
			repositorioProducto.save(short6);
			Producto short7 = new Short("Short","Short Celtics",1200,2400,10,"New Balance",new String[]{"M","L","XL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638899746/th_5_fg8ei0.jpg","Celtics");
			repositorioProducto.save(short7);
			Producto short8 = new Short("Short","Short Chealse",3000,4000,10,"Nike",new String[]{"M","L","XL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1638899746/th_4_cybiwt.jpg","Chelsea");
			repositorioProducto.save(short8);
			Producto short9 = new Short("Short","Short Gris Puma",2000,3000,10,"Puma",new String[]{"M","L","XL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1639135824/th_23_dx8vjr.jpg","Puma");
			repositorioProducto.save(short9);
			Producto short10 = new Short("Short","Short Adidas",2500,3500,10,"Adidas",new String[]{"M","L","XL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1639135824/th_25_as9s9d.jpg","Adidas");
			repositorioProducto.save(short10);
			Producto short11 = new Short("Short","Short Blanco/Rojo",1500,2500,10,"Macron",new String[]{"M","L","XL"},"https://res.cloudinary.com/dboyuvklh/image/upload/v1639135824/th_24_wfhszw.jpg","Macron");
			repositorioProducto.save(short11);




		};
	}

}
