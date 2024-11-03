package com.mistergold.mistergold.application.domain.order;

import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.client.Client;
import com.mistergold.mistergold.configuration.web.enums.OrderStatusEnum;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private Instant moment;
    private OrderStatusEnum orderStatus;
    private Client client;
    private Set<OrderItem> items =  new HashSet<>();
    private InfoActivation infoActivation;

    public Double totalPrice() {
        return items.stream().mapToDouble(orderItem -> orderItem.getPrice() * orderItem.getQuantity()).sum();
    }
}
