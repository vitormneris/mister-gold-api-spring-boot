package com.mistergold.mistergold.adapters.persistence.mappers;

import com.mistergold.mistergold.adapters.persistence.entities.InfoActivationEntity;
import com.mistergold.mistergold.adapters.persistence.entities.client.AddressEntity;
import com.mistergold.mistergold.adapters.persistence.entities.client.ClientEntity;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.client.Address;
import com.mistergold.mistergold.application.domain.client.Client;

import java.util.List;
import java.util.stream.Collectors;

import com.mistergold.mistergold.application.domain.order.Order;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

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
        clientEntity.setRole(client.getRole());
        clientEntity.setOrderId(client.getOrder().getId());
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
        client.setRole(clientEntity.getRole());
        client.setOrder(Order.builder().id(clientEntity.getOrderId()).build());
        client.setAddress(mapToDomain(clientEntity.getAddress()));
        client.setInfoActivation(mapToDomain(clientEntity.getInfoActivation()));

        return client;
    }

    Address mapToDomain(AddressEntity addressEntity);
    AddressEntity mapToEntity(Address address);

    default PageResponse<Client> mapToPageResponseDomain(Page<ClientEntity> clientEntities) {
        int previousPage = clientEntities.hasPrevious() ? clientEntities.getNumber() - 1 : clientEntities.getNumber();
        int nextPage = clientEntities.hasNext() ? clientEntities.getNumber() + 1 : clientEntities.getNumber();

        List<Client> clients = clientEntities.getContent().stream().map(this::mapToDomain).collect(Collectors.toList());

        return PageResponse.<Client>builder()
                .pageSize(clientEntities.getNumberOfElements())
                .totalElements(clientEntities.getTotalElements())
                .currentPage(clientEntities.getNumber())
                .previousPage(previousPage)
                .nextPage(nextPage)
                .content(clients)
                .totalPages(clientEntities.getTotalPages())
                .build();
    }

    InfoActivationEntity mapToEntity(InfoActivation infoActivation);
    InfoActivation mapToDomain(InfoActivationEntity infoActivationEntity);
}
