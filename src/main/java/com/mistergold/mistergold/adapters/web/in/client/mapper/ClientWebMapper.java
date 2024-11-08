package com.mistergold.mistergold.adapters.web.in.client.mapper;

import com.mistergold.mistergold.adapters.persistence.entities.client.AddressEntity;
import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import com.mistergold.mistergold.adapters.web.in.client.dto.AddressDTO;
import com.mistergold.mistergold.adapters.web.in.client.dto.ClientDTO;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.client.Address;
import com.mistergold.mistergold.application.domain.client.Client;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientWebMapper {
    List<ClientDTO> mapToDTO(List<Client> clients);

    default ClientDTO mapToDTO(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .phone(client.getPhone())
                .role(client.getRole())
                .password(client.getPassword())
                .address(mapToDTO(client.getAddress()))
                .infoActivation(mapToDTO(client.getInfoActivation()))
                .build();
    }

    default Client mapToDomain(ClientDTO clientDTO) {
        Client client = new Client();

        client.setId(clientDTO.id());
        client.setName(clientDTO.name());
        client.setEmail(clientDTO.email());
        client.setPassword(clientDTO.password());
        client.setPhone(clientDTO.phone());
        client.setAddress(mapToDomain(clientDTO.address()));
        client.setInfoActivation(mapToDomain(clientDTO.infoActivation()));

        return client;
    }

    Address mapToDomain(AddressDTO addressDTO);
    AddressDTO mapToDTO(Address address);

    InfoActivationDTO mapToDTO(InfoActivation infoActivation);
    InfoActivation mapToDomain(InfoActivationDTO infoActivation);
}
