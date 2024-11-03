package com.mistergold.mistergold.adapters.persistence.mappers;

import com.mistergold.mistergold.adapters.persistence.entities.InfoActivationEntity;
import com.mistergold.mistergold.adapters.persistence.entities.client.AddressEntity;
import com.mistergold.mistergold.adapters.persistence.entities.client.ClientEntity;
import com.mistergold.mistergold.adapters.web.in.client.dto.AddressDTO;
import com.mistergold.mistergold.adapters.web.in.client.dto.ClientDTO;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.client.Address;
import com.mistergold.mistergold.application.domain.client.Client;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientPersistenceMapper {
    List<Client> mapListToDomain(List<ClientEntity> entities);

    default ClientEntity mapToEntity(Client client) {
        ClientEntity clientEntity = new ClientEntity();

        clientEntity.setId(client.getId());
        clientEntity.setName(client.getName());
        clientEntity.setEmail(client.getEmail());
        clientEntity.setPassword(client.getPassword());
        clientEntity.setPhone(client.getPhone());
        clientEntity.setAddress(mapToEntity(client.getAddress()));
        clientEntity.setInfoActivation(mapToEntity(client.getInfoActivation()));

        return clientEntity;
    }

    default Client mapToDomain(ClientEntity clientEntity) {
        Client client = new Client();

        client.setId(clientEntity.getId());
        client.setName(clientEntity.getName());
        client.setEmail(clientEntity.getEmail());
        client.setPassword(clientEntity.getPassword());
        client.setPhone(clientEntity.getPhone());
        client.setAddress(mapToDomain(clientEntity.getAddress()));
        client.setInfoActivation(mapToDomain(clientEntity.getInfoActivation()));

        return client;
    }

    Address mapToDomain(AddressEntity addressEntity);
    AddressEntity mapToEntity(Address address);

    InfoActivationEntity mapToEntity(InfoActivation infoActivation);
    InfoActivation mapToDomain(InfoActivationEntity infoActivationEntity);
}
