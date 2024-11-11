package com.mistergold.mistergold.adapters.persistence.mappers;

import com.mistergold.mistergold.adapters.persistence.entities.InfoActivationEntity;
import com.mistergold.mistergold.adapters.persistence.entities.order.OrderEntity;
import com.mistergold.mistergold.adapters.persistence.entities.order.OrderItemEntity;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.client.Client;
import com.mistergold.mistergold.application.domain.order.Order;
import com.mistergold.mistergold.application.domain.order.OrderItem;
import com.mistergold.mistergold.application.domain.product.Product;
import com.mistergold.mistergold.configuration.web.enums.OrderStatusEnum;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderPersistenceMapper {
    List<Order> mapListToDomain(List<OrderEntity> entities);

    default OrderEntity mapToEntity(Order order) {
        Set<OrderItemEntity> entities = order.getItems().stream().map(orderItem ->
            OrderItemEntity.builder()
                    .price(orderItem.getPrice())
                    .productId(orderItem.getProduct().getId())
                    .quantity(orderItem.getQuantity()).build()).collect(Collectors.toSet());

        return OrderEntity.builder()
                .id(order.getId())
                .items(entities)
                .orderStatus(order.getOrderStatus().getCode())
                .clientId(order.getClient().getId())
                .totalPrice(order.totalPrice())
                .moment(order.getMoment())
                .infoActivation(mapToEntity(order.getInfoActivation()))
                .build();
    }

    default Order mapToDomain(OrderEntity orderEntity) {
        Set<OrderItem> orderItems = orderEntity.getItems().stream().map(orderItemEntity ->
                OrderItem.builder()
                        .price(orderItemEntity.getPrice())
                        .product(Product.builder().id(orderItemEntity.getProductId()).build())
                        .quantity(orderItemEntity.getQuantity()).build()).collect(Collectors.toSet());

        return Order.builder()
                .id(orderEntity.getId())
                .items(orderItems)
                .orderStatus(OrderStatusEnum.valueOf(orderEntity.getOrderStatus()))
                .client(new Client(orderEntity.getClientId()))
                .moment(orderEntity.getMoment())
                .infoActivation(mapToDomain(orderEntity.getInfoActivation()))
                .build();
    }

    default PageResponse<Order> mapToPageResponseDomain(Page<OrderEntity> orderEntities) {
        int previousPage = orderEntities.hasPrevious() ? orderEntities.getNumber() - 1 : orderEntities.getNumber();
        int nextPage = orderEntities.hasNext() ? orderEntities.getNumber() + 1 : orderEntities.getNumber();

        List<Order> orders = orderEntities.getContent().stream().map(this::mapToDomain).collect(Collectors.toList());

        return PageResponse.<Order>builder()
                .pageSize(orderEntities.getNumberOfElements())
                .totalElements(orderEntities.getTotalElements())
                .currentPage(orderEntities.getNumber())
                .previousPage(previousPage)
                .nextPage(nextPage)
                .content(orders)
                .totalPages(orderEntities.getTotalPages())
                .build();
    }

    InfoActivationEntity mapToEntity(InfoActivation infoActivation);
    InfoActivation mapToDomain(InfoActivationEntity infoActivationEntity);
}
