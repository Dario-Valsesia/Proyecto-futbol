package com.mindhub.proyectoFinal.controladores;

import com.mindhub.proyectoFinal.servicio.MailServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ControladorMail {
    @Autowired
    MailServicio mailServicio;

    @PostMapping("/mail")
    public ResponseEntity<Object> generarMail(@RequestParam String destino,@RequestParam String nombreUsuario){
        String asunto = "Subscripción a BALL d`or";
        String primerParrafo = "¡Hola, "+nombreUsuario+"!, gracias por unirte a nuestro equipo\n\n";
        String segundoParrafo = "Ball d`or es una empresa deportiva dedicada a la organizacion de torneos de futbol y a la venta de articulos deportivos en general. En nuestras newsletter recibiras informacion actualizada respecto a ofertas y promociones de nuestro Marketplace y de nuestros servicios.\n\n";
        String tercerParrafo = "En Ball d`or nos interesa conocerte mas y que nos des una opinion sobre nuestros productos y servicios, es por eso que dejamos nuestras redes sociales para que interactues con nosotros y satisfacer cada una de las necesidades de quienes forman parte de este gran equipo.\n\n";
        String redesSociales = "Instragram: https://www.instagram.com\n"+"Whatsapp: 3514526354\n"+"Facebook: https://www.facebook.com";
        String text = primerParrafo+segundoParrafo+tercerParrafo+redesSociales;
        mailServicio.enviarMail(destino,asunto,text);
        return new ResponseEntity<>("Email enviado", HttpStatus.CREATED);
    }
}
