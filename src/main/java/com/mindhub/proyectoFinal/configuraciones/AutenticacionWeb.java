package com.mindhub.proyectoFinal.configuraciones;

import com.mindhub.proyectoFinal.modelos.Cliente;
import com.mindhub.proyectoFinal.repositorios.RepositorioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AutenticacionWeb  extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    RepositorioCliente repositorioCliente;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(username -> {
                Cliente cliente = repositorioCliente.findByEmail(username);
                if(cliente.getEmail().equals("dariovalsesia14@gmail.com")){
                    return new User(cliente.getEmail(), cliente.getPassword(), AuthorityUtils.createAuthorityList("ADMIN","CLIENTE"));
                }
                else if(cliente!=null){
                    return new User(cliente.getEmail(), cliente.getPassword(), AuthorityUtils.createAuthorityList("CLIENTE"));
                }
                else{
                    throw new UsernameNotFoundException("Unknown user: " +username);
                }
            });
    }
}
