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
                        .antMatchers("/web/index.html","/web/manager.html","/web/recuperar-contraseña","/web/estilos/**").permitAll()
                        .antMatchers(HttpMethod.POST,"/api/login","/api/enviar-contraseña").permitAll()
                        .antMatchers(HttpMethod.PUT, "/api/clientes/olvido-contraseña").permitAll()
                        .antMatchers("/web/paginas/**").hasAuthority("CLIENTE")
                        .antMatchers("/api/productos").hasAuthority("CLIENTE")
                        .antMatchers("/web/paginas/panel-admin.html").hasAuthority("ADMIN")
                        .antMatchers(HttpMethod.PUT,"/api/productos/**").hasAuthority("ADMIN")
                        .antMatchers(HttpMethod.POST,"/api/productos").hasAuthority("ADMIN");

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
