package com.mistergold.mistergold.adapters.web.in.order.mapper;

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
import com.mistergold.mistergold.application.domain.category.Category;
import com.mistergold.mistergold.application.domain.client.Address;
import com.mistergold.mistergold.application.domain.client.Client;
import com.mistergold.mistergold.application.domain.order.Order;
import com.mistergold.mistergold.application.domain.order.OrderItem;
import com.mistergold.mistergold.application.domain.product.Product;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderWebMapper {
    List<OrderDTO> mapToDTO(List<Order> orders);

    default OrderDTO mapToDTO(Order order) {
        Set<OrderItemDTO> orderItemDTOS = order.getItems().stream().map(orderItem -> {

            Set<CategoryDTO> categoryDTOS = orderItem.getProduct().getCategories().stream().map(category -> CategoryDTO.builder()
                .id(orderItem.getProduct().getId())
                .name(orderItem.getProduct().getName())
                .description(orderItem.getProduct().getDescription())
                .imageUrl(orderItem.getProduct().getImageUrl())
                .infoActivation(mapToDTO(orderItem.getProduct().getInfoActivation()))
                .build()).collect(Collectors.toSet());

            ProductDTO productDTO = ProductDTO.builder()
                    .id(orderItem.getProduct().getId())
                    .name(orderItem.getProduct().getName())
                    .description(orderItem.getProduct().getDescription())
                    .imageUrl(orderItem.getProduct().getImageUrl())
                    .price(orderItem.getProduct().getPrice())
                    .quantity(orderItem.getProduct().getQuantity())
                    .categories(categoryDTOS)
                    .infoActivation(mapToDTO(orderItem.getProduct().getInfoActivation()))
                    .build();

            return OrderItemDTO.builder()
                    .product(productDTO)
                    .price(orderItem.getPrice())
                    .quantity(orderItem.getQuantity())
                    .build();
        }).collect(Collectors.toSet());

        ClientDTO clientDTO = ClientDTO.builder()
                .id(order.getClient().getId())
                .name(order.getClient().getName())
                .email(order.getClient().getEmail())
                .phone(order.getClient().getPhone())
                .infoActivation(mapToDTO(order.getClient().getInfoActivation()))
                .role(order.getClient().getRole())
                .address(mapToDTO(order.getClient().getAddress()))
                .build();

        return OrderDTO.builder()
                .id(order.getId())
                .items(orderItemDTOS)
                .client(clientDTO)
                .orderMessage(order.getOrderStatus().getMessage())
                .moment(order.getMoment())
                .totalPrice(order.totalPrice())
                .infoActivation(mapToDTO(order.getInfoActivation()))
                .build();
    }

    default Order mapToDomain(OrderDTO orderDTO) {
        Set<OrderItem> orderItems = orderDTO.items().stream().map(orderItemDTO -> OrderItem.builder()
            .product(Product.builder().id(orderItemDTO.product().id()).build())
            .quantity(orderItemDTO.quantity())
            .build()).collect(Collectors.toSet());

        return Order.builder()
                .client(new Client(orderDTO.client().id()))
                .orderStatus(orderDTO.orderStatus())
                .items(orderItems)
                .build();
    }

    AddressDTO mapToDTO(Address address);
    PageResponseDTO<OrderDTO> mapToPageResponseDto(PageResponse<Order> pageResponse);
    InfoActivationDTO mapToDTO(InfoActivation infoActivation);
    InfoActivation mapToDomain(InfoActivationDTO infoActivation);
}
