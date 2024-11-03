package com.mistergold.mistergold.application.ports.in.order;

import com.mistergold.mistergold.application.domain.order.Order;

public interface UpdateOrderUseCase {
    Order update(Order order, String id);
}
