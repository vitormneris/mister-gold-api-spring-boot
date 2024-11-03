package com.mistergold.mistergold.application.ports.out.order;

import com.mistergold.mistergold.application.domain.order.Order;

public interface UpdateOrderPort {
    Order update(Order order, String id);
}
