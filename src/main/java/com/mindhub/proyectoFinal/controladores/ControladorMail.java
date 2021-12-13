package com.mindhub.proyectoFinal.controladores;

import com.mindhub.proyectoFinal.modelos.Cliente;
import com.mindhub.proyectoFinal.repositorios.RepositorioCliente;
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

    @Autowired
    RepositorioCliente repositorioCliente;

    @PostMapping("/mail")
    public ResponseEntity<Object> generarMail(@RequestParam String destino,
                                              @RequestParam String nombreUsuario){
        String asunto = "Subscripción a Ball D`or";
        String primerParrafo = "¡Hola, "+nombreUsuario+"!, gracias por unirte a nuestro equipo.\n\n";
        String segundoParrafo = "Ball D`or es una empresa deportiva dedicada a la organización de torneos de fútbol y a la venta de artículos deportivos en general. En nuestras newsletters recibirás información actualizada respecto a ofertas y promociones de nuestro Marketplace y de nuestros servicios.\n\n";
        String tercerParrafo = "En Ball D`or nos interesa conocerte más y que nos des una opinión sobre nuestros productos y servicios, es por eso que dejamos nuestras redes sociales para que interactues con nosotros y satisfacer cada una de las necesidades de quienes forman parte de este gran equipo.\n\n";
        String redesSociales = "Facebook: https://www.facebook.com\n"+"Instragram: https://www.instagram.com\n"+"Whatsapp: 3514526354";
        String text = primerParrafo+segundoParrafo+tercerParrafo+redesSociales;
        mailServicio.enviarMail(destino,asunto,text);
        return new ResponseEntity<>("Registro enviado", HttpStatus.CREATED);
    }

    @PostMapping("/consulta")
    public ResponseEntity<Object> respuestaConsulta(
            @RequestParam String nombre,
            @RequestParam String email,
            @RequestParam String asunto,
            @RequestParam String mensaje){

        String asunto1 = "Consulta recibida";

        String parrafo1 = "Hola, "+nombre+" "+".\n\n";
        String parrafo2 = "Hemos recibido tu mensaje satisfactoriamente. Estaremos comunicandonos con usted a la brevedad posible. A continuación se le envía un resúmen de su consulta:\n\n";
        String parrafo3 = "Asunto: "+asunto+"\n\n";
        String parrafo4 = "Mensaje: "+mensaje+"\n\n";
        String redesSociales = "Facebook: https://www.facebook.com\n"+"Instragram: https://www.instagram.com\n"+"Whatsapp: 3514526354";

        String cuerpoMensaje = parrafo1 + parrafo2 + parrafo3 + parrafo4 + redesSociales;
        mailServicio.enviarMail(email, asunto1, cuerpoMensaje);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/enviar-contraseña")
    public ResponseEntity<Object> cambioContraseña(@RequestParam String email){

        Cliente cliente = repositorioCliente.findByEmail(email);

        if(cliente == null){
            return new ResponseEntity<>("Cliente no registrado.", HttpStatus.FORBIDDEN);
        }

        String asunto1 = "Solicitud de cambio de contraseña";

        String parrafo1 = "Hola, hemos recibido tu solicitud de cambio de contraseña. Sigue el siguiente link para continuar con tu cambio:\n\n";
        String parrafo2 = "https://ballon-dor.herokuapp.com/web/recuperar-contraseña.html\n\n";
        String redesSociales = "Facebook: https://www.facebook.com\n"+"Instragram: https://www.instagram.com\n"+"Whatsapp: 3514526354";

        String cuerpoEmail = parrafo1 + parrafo2 + redesSociales;
        mailServicio.enviarMail(email, asunto1, cuerpoEmail);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
