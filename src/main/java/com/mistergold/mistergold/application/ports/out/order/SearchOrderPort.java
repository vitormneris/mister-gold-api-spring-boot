package com.mistergold.mistergold.application.ports.out.order;

import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.order.Order;

public interface SearchOrderPort {
    PageResponse<Order> findByPagination(Boolean isActive, Integer page, Integer pageSize, String name);
    Order findById(String id);
}
