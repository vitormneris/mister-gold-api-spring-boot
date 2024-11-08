package com.mistergold.mistergold.configuration.web.auth;

import com.mistergold.mistergold.adapters.persistence.entities.client.ClientEntity;
import com.mistergold.mistergold.adapters.persistence.mappers.AdministratorPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.mappers.ClientPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.AdministratorRepository;
import com.mistergold.mistergold.adapters.persistence.repositories.ClientRepository;
import com.mistergold.mistergold.application.domain.administrator.Administrator;
import com.mistergold.mistergold.application.domain.client.Client;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.auth.service.TokenService;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FilterToken extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final ClientRepository clientRepository;
    private final AdministratorRepository administratorRepository;
    private final ClientPersistenceMapper clientPersistenceMapper;
    private final AdministratorPersistenceMapper administratorPersistenceMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token;
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            token = authorizationHeader.replace("Bearer ", "");
            var subject = tokenService.getSubject(token);

            Optional<ClientEntity> optionalClient = clientRepository.findByEmail(subject);
            if (optionalClient.isPresent()) {
                Client user = clientPersistenceMapper.mapToDomain(optionalClient.get());
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                Administrator user = administratorPersistenceMapper.mapToDomain(administratorRepository.findByEmail(subject)
                        .orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0011)));
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
