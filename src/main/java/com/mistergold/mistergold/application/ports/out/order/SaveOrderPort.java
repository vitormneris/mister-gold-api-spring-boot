package com.mistergold.mistergold.application.ports.out.order;

import com.mistergold.mistergold.application.domain.order.Order;

public interface SaveOrderPort {
    Order save(Order order);
}
