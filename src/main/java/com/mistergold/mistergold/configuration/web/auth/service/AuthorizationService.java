package com.mistergold.mistergold.configuration.web.auth.service;

import com.mistergold.mistergold.adapters.persistence.entities.client.ClientEntity;
import com.mistergold.mistergold.adapters.persistence.mappers.AdministratorPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.mappers.ClientPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.AdministratorRepository;
import com.mistergold.mistergold.adapters.persistence.repositories.ClientRepository;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {
    private final ClientRepository clientRepository;
    private final AdministratorRepository administratorRepository;
    private final ClientPersistenceMapper clientPersistenceMapper;
    private final AdministratorPersistenceMapper administratorPersistenceMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ClientEntity> objClient = clientRepository.findByEmail(username);
        if (objClient.isPresent()) return clientPersistenceMapper.mapToDomain(objClient.get());
        return administratorPersistenceMapper.mapToDomain(administratorRepository.findByEmail(username).orElseThrow());
    }
}
