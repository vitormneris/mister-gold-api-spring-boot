package com.mistergold.mistergold.application.ports.in.order;

import com.mistergold.mistergold.application.domain.order.Order;

public interface DeleteOrderUseCase {
    void delete(String id);
    Order inactivate(String id);
}
