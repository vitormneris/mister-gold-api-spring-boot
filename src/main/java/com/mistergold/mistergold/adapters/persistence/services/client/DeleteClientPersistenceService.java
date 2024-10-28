package com.mistergold.mistergold.adapters.persistence.services.client;

import com.mistergold.mistergold.adapters.persistence.entities.client.ClientEntity;
import com.mistergold.mistergold.adapters.persistence.mappers.ClientPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.ClientRepository;
import com.mistergold.mistergold.application.domain.client.Client;
import com.mistergold.mistergold.application.ports.out.client.DeleteClientPort;
import com.mistergold.mistergold.configuration.web.advice.exception.DataIntegratyViolationException;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class DeleteClientPersistenceService implements DeleteClientPort {
    private final ClientRepository clientRepository;
    private final ClientPersistenceMapper mapper;

    @Override
    public void delete(String id) {
        clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0001));
        clientRepository.deleteById(id);
    }

    @Override
    public Client inactivate(String id) {
        ClientEntity client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0001));
        if (client.getInfoActivation().getIsActive()) {
            client.getInfoActivation().setIsActive(false);
            client.getInfoActivation().setDeactivationDate(Instant.now());
        } else throw new DataIntegratyViolationException(RunErrorEnum.ERR0004);
        return mapper.mapToDomain(clientRepository.save(client));
    }
}
