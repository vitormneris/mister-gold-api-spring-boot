package com.mistergold.mistergold.adapters.persistence.services.client;

import com.mistergold.mistergold.adapters.persistence.entities.client.AddressEntity;
import com.mistergold.mistergold.adapters.persistence.entities.client.ClientEntity;
import com.mistergold.mistergold.adapters.persistence.mappers.ClientPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.ClientRepository;
import com.mistergold.mistergold.application.domain.client.Client;
import com.mistergold.mistergold.application.ports.out.client.UpdateClientPort;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateClientPersistenceService implements UpdateClientPort {
    private final ClientRepository clientRepository;
    private final ClientPersistenceMapper mapper;

    @Override
    public Client update(Client clientNew, String id) {
        ClientEntity clientOld = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0001));
        clientOld.setName(clientNew.getName() == null ? clientOld.getName() : clientNew.getName());
        clientOld.setEmail(clientNew.getEmail() == null ? clientOld.getEmail() : clientNew.getEmail());
        clientOld.setPhone(clientNew.getPhone() == null ? clientOld.getPhone() : clientNew.getPhone());

        clientOld.setAddress(clientNew.getAddress() == null ? clientOld.getAddress() :
            AddressEntity.builder()
                    .state(clientNew.getAddress().getState() == null ? clientOld.getAddress().getState() : clientNew.getAddress().getState())
                    .city(clientNew.getAddress().getCity() == null ? clientOld.getAddress().getCity() : clientNew.getAddress().getCity())
                    .neighborhood(clientNew.getAddress().getNeighborhood() == null ? clientOld.getAddress().getNeighborhood() : clientNew.getAddress().getNeighborhood())
                    .street(clientNew.getAddress().getStreet() == null ? clientOld.getAddress().getStreet() : clientNew.getAddress().getStreet())
                    .postalCode(clientNew.getAddress().getPostalCode() == null ? clientOld.getAddress().getPostalCode() : clientNew.getAddress().getPostalCode())
                    .number(clientNew.getAddress().getNumber() == null ? clientOld.getAddress().getNumber() : clientNew.getAddress().getNumber())
                    .build());

        return mapper.mapToDomain(clientRepository.save(clientOld));
    }
}
