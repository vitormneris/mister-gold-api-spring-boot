package com.mistergold.mistergold.application.ports.out.order;

import com.mistergold.mistergold.application.domain.order.Order;

public interface DeleteOrderPort {
    void delete(String id);
    Order inactivate(String id);
}
