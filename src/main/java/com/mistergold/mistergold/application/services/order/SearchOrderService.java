package com.mistergold.mistergold.application.services.order;

import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.order.Order;
import com.mistergold.mistergold.application.ports.in.order.SearchOrderUseCase;
import com.mistergold.mistergold.application.ports.out.order.SearchOrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchOrderService implements SearchOrderUseCase {
    private final SearchOrderPort searchOrderPort;

    @Override
    public PageResponse<Order> findByPagination(Boolean isActive, Integer page, Integer pageSize, String name) {
        return searchOrderPort.findByPagination(isActive, page, pageSize, name);
    }

    @Override
    public Order findById(String id) {
        return searchOrderPort.findById(id);
    }
}
