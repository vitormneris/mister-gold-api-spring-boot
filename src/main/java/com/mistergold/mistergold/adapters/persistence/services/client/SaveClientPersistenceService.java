package com.mistergold.mistergold.adapters.persistence.services.client;

import com.mistergold.mistergold.adapters.persistence.mappers.ClientPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.ClientRepository;
import com.mistergold.mistergold.application.domain.client.Client;
import com.mistergold.mistergold.application.ports.out.client.SaveClientPort;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveClientPersistenceService implements SaveClientPort {
    private final ClientRepository userRepository;
    private final ClientPersistenceMapper mapper;

    @Override
    public Client save(Client client) {
        return mapper.mapToDomain(userRepository.save(mapper.mapToEntity(client)));
    }
}
