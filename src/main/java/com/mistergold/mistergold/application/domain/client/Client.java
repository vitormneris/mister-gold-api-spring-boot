package com.mistergold.mistergold.application.domain.client;

import com.mistergold.mistergold.application.domain.abstracts.UserAbstract;

import com.mistergold.mistergold.application.domain.order.Order;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client extends UserAbstract {
    private String phone;
    private Address address;
    private Set<Order> order;

    public Client(String id) {
        this.id = id;
    }
}
