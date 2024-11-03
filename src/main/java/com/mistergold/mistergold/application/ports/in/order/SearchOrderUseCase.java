package com.mistergold.mistergold.application.ports.in.order;

import com.mistergold.mistergold.application.domain.order.Order;

import java.util.List;

public interface SearchOrderUseCase {
    List<Order> findAll();
    Order findById(String id);
}
