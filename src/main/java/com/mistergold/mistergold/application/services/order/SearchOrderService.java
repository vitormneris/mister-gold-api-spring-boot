package com.mistergold.mistergold.application.services.order;

import com.mistergold.mistergold.application.domain.order.Order;
import com.mistergold.mistergold.application.ports.in.order.SearchOrderUseCase;
import com.mistergold.mistergold.application.ports.out.client.SearchClientPort;
import com.mistergold.mistergold.application.ports.out.order.SearchOrderPort;
import com.mistergold.mistergold.application.ports.out.product.SearchProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchOrderService implements SearchOrderUseCase {
    private final SearchOrderPort searchOrderPort;
    private final SearchClientPort searchClientPort;
    private final SearchProductPort searchProductPort;

    @Override
    public Order findById(String id) {
        Order order = searchOrderPort.findById(id);
        order.setClient(searchClientPort.findById(order.getClient().getId()));
        order.getItems().forEach(orderItem -> orderItem.setProduct(searchProductPort.findById(orderItem.getProduct().getId())));
        return order;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = searchOrderPort.findAll();
        orders.forEach(order -> {
            order.setClient(searchClientPort.findById(order.getClient().getId()));
            order.getItems().forEach(orderItem -> orderItem.setProduct(searchProductPort.findById(orderItem.getProduct().getId())));
        });
        return orders;
    }
}
