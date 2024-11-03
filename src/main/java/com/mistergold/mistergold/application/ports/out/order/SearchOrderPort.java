package com.mistergold.mistergold.application.ports.out.order;

import com.mistergold.mistergold.application.domain.order.Order;

import java.util.List;

public interface SearchOrderPort {
    List<Order> findAll();
    Order findById(String id);
}
