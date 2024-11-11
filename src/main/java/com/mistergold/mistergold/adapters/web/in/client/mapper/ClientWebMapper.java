package com.mistergold.mistergold.adapters.web.in.client.mapper;

import com.mistergold.mistergold.adapters.web.PageResponseDTO;
import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import com.mistergold.mistergold.adapters.web.in.category.dto.CategoryDTO;
import com.mistergold.mistergold.adapters.web.in.client.dto.AddressDTO;
import com.mistergold.mistergold.adapters.web.in.client.dto.ClientDTO;
import com.mistergold.mistergold.adapters.web.in.order.dto.OrderDTO;
import com.mistergold.mistergold.adapters.web.in.order.dto.OrderItemDTO;
import com.mistergold.mistergold.adapters.web.in.product.dto.ProductDTO;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.client.Address;
import com.mistergold.mistergold.application.domain.client.Client;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.mistergold.mistergold.application.domain.order.Order;
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
                .order(mapToDTO(client.getOrder()))
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

    default OrderDTO mapToDTO(Order order) {
        Set<OrderItemDTO> orderItemDTOS = order.getItems().stream().map(orderItem -> {

            List<CategoryDTO> categoryDTOS = orderItem.getProduct().getCategories().stream().map(category -> {
                return CategoryDTO.builder()
                        .id(orderItem.getProduct().getId())
                        .name(orderItem.getProduct().getName())
                        .description(orderItem.getProduct().getDescription())
                        .imageUrl(orderItem.getProduct().getImageUrl())
                        .infoActivation(mapToDTO(orderItem.getProduct().getInfoActivation()))
                        .build();
            }).toList();

            ProductDTO productDTO = ProductDTO.builder()
                    .id(orderItem.getProduct().getId())
                    .name(orderItem.getProduct().getName())
                    .description(orderItem.getProduct().getDescription())
                    .imageUrl(orderItem.getProduct().getImageUrl())
                    .price(orderItem.getProduct().getPrice())
                    .size(orderItem.getProduct().getSize())
                    .color(orderItem.getProduct().getColor())
                    .weight(orderItem.getProduct().getWeight())
                    .quantity(orderItem.getProduct().getQuantity())
                    .material(orderItem.getProduct().getMaterial())
                    .categories(categoryDTOS)
                    .infoActivation(mapToDTO(orderItem.getProduct().getInfoActivation()))
                    .build();

            return OrderItemDTO.builder()
                    .product(productDTO)
                    .price(orderItem.getPrice())
                    .quantity(orderItem.getQuantity())
                    .build();
        }).collect(Collectors.toSet());

        return OrderDTO.builder()
                .id(order.getId())
                .items(orderItemDTOS)
                .orderStatus(order.getOrderStatus())
                .moment(order.getMoment())
                .totalPrice(order.totalPrice())
                .infoActivation(mapToDTO(order.getInfoActivation()))
                .build();
    }

    Address mapToDomain(AddressDTO addressDTO);
    AddressDTO mapToDTO(Address address);
    PageResponseDTO<ClientDTO> mapToPageResponseDto(PageResponse<Client> pageResponse);
    InfoActivationDTO mapToDTO(InfoActivation infoActivation);
    InfoActivation mapToDomain(InfoActivationDTO infoActivation);
}
