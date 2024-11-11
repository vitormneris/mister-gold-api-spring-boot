package com.mistergold.mistergold.adapters.persistence.services.client;

import com.mistergold.mistergold.adapters.persistence.mappers.ClientPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.ClientRepository;
import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.client.Client;
import com.mistergold.mistergold.application.ports.out.client.SearchClientPort;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchClientPersistenceService implements SearchClientPort {
    private final ClientRepository clientRepository;
    private final ClientPersistenceMapper mapper;

    @Override
    public PageResponse<Client> findByPagination(Boolean isActive, Integer page, Integer pageSize, String name) {
        return mapper.mapToPageResponseDomain(
                clientRepository.findByPagination(isActive, PageRequest.of(page, pageSize), (name == null) ? "" : name)
        );
    }

    @Override
    public Client findById(String id) {
        return mapper.mapToDomain(clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(RunErrorEnum.ERR0001))
        );
    }

    @Override
    public Client findByEmail(String email) {
        return mapper.mapToDomain(clientRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException(RunErrorEnum.ERR0001))
        );
    }

    @Override
    public Boolean checkEmailExists(String email) {
        return clientRepository.findByEmail(email).isPresent();
    }
}
