package com.mistergold.mistergold.application.ports.in.order;

import com.mistergold.mistergold.application.domain.order.Order;

public interface SaveOrderUseCase {
    Order save(Order order);
}
