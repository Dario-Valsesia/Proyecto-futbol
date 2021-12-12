package com.mindhub.proyectoFinal.configuraciones;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
public class AutorizacionWeb extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/clientes").permitAll()
                .antMatchers("/web/index.html").permitAll()
                .antMatchers("/web/manager.html").permitAll()
                .antMatchers("/web/paginas/canchasReservas.html").hasAnyAuthority("CLIENTE", "ADMIN")
                .antMatchers("/web/paginas/configuracion.html").hasAnyAuthority("CLIENTE", "ADMIN")
                .antMatchers("/web/paginas/contacto.html").hasAnyAuthority("CLIENTE", "ADMIN")
                .antMatchers("/web/paginas/home.html").hasAnyAuthority("CLIENTE", "ADMIN")
                .antMatchers("/web/paginas/misCompras.html").hasAnyAuthority("CLIENTE", "ADMIN")
                .antMatchers("/web/paginas/sobre-nosotros.html").hasAnyAuthority("CLIENTE", "ADMIN")
                .antMatchers("/web/paginas/tienda.html").hasAnyAuthority("CLIENTE", "ADMIN")
                .antMatchers("/api/canchas").hasAnyAuthority("CLIENTE", "ADMIN")
                .antMatchers("/api/reservas").hasAnyAuthority("CLIENTE", "ADMIN")
                .antMatchers("/api/reservar").hasAnyAuthority("CLIENTE", "ADMIN")
                .antMatchers("/api//reservas/{id}").hasAnyAuthority("CLIENTE", "ADMIN")
                .antMatchers("/api/cliente/actual").hasAnyAuthority("CLIENTE", "ADMIN")
                .antMatchers("/api/cliente/actual/personal").hasAnyAuthority("CLIENTE", "ADMIN")
                .antMatchers("/cliente/actual/password").hasAnyAuthority("CLIENTE", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/clientes").hasAuthority("ADMIN")
                .antMatchers("/web/paginas/panel-admin.html").hasAuthority("ADMIN");
        http.formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/api/login");
        http.logout().logoutUrl("/api/logout").deleteCookies("JSESSIONID");
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.exceptionHandling().authenticationEntryPoint(((request, response, authException) -> {
            if(request.getRequestURI().contains("/web")){
                response.sendRedirect("/web/index.html");
            }
        }));
        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));
        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
    }
    private void clearAuthenticationAttributes(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}
