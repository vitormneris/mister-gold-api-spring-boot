package com.mistergold.mistergold.adapters.web.in.client.mapper;

import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import com.mistergold.mistergold.adapters.web.in.client.dto.ClientDTO;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.client.Client;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientWebMapper {
    List<ClientDTO> mapToDTO(List<Client> clients);
    ClientDTO mapToDTO(Client client);
    Client mapToDomain(ClientDTO clientDTO);
    InfoActivationDTO mapToDTO(InfoActivation infoActivation);
    InfoActivation mapToDomain(InfoActivationDTO infoActivation);
}
