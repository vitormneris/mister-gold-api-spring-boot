package com.mistergold.mistergold.configuration.web.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final FilterToken filterToken;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.DELETE, "/produtos/{id}/deletar").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.DELETE, "/produtos/{id}/desativar").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.PUT, "/produtos/{id}/atualizar").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.POST, "/produtos/salvar").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.GET, "/produtos/{id}/id").permitAll()
                        .requestMatchers(HttpMethod.GET, "/produtos").permitAll()

                        .requestMatchers(HttpMethod.DELETE, "/categorias/{id}/deletar").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.DELETE, "/categorias/{id}/desativar").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.PUT, "/categorias/{id}/atualizar").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.POST, "/categorias/salvar").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.GET, "/categorias/{id}/id").permitAll()
                        .requestMatchers(HttpMethod.GET, "/categorias").permitAll()

                        .requestMatchers(HttpMethod.PUT, "/clientes/{id}/atualizar").hasRole("CLIENT")

                        .requestMatchers(HttpMethod.POST, "/clientes/salvar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/administradores/salvar").permitAll()

                        .requestMatchers(HttpMethod.POST, "/autorizacao/login").permitAll()
                        .anyRequest().authenticated()
                ).sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filterToken, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
