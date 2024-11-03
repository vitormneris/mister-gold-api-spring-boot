package com.mistergold.mistergold.application.services.order;

import com.mistergold.mistergold.application.domain.order.Order;
import com.mistergold.mistergold.application.ports.in.order.UpdateOrderUseCase;
import com.mistergold.mistergold.application.ports.out.client.SearchClientPort;
import com.mistergold.mistergold.application.ports.out.order.UpdateOrderPort;
import com.mistergold.mistergold.application.ports.out.product.SearchProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateOrderService implements UpdateOrderUseCase {
    private final UpdateOrderPort updateOrderPort;
    private final SearchClientPort searchClientPort;
    private final SearchProductPort searchProductPort;

    @Override
    public Order update(Order orderNew, String id) {
        orderNew.setId(id);
        Order order = updateOrderPort.update(orderNew, id);
        order.setClient(searchClientPort.findById(order.getClient().getId()));
        order.getItems().forEach(orderItem -> orderItem.setProduct(searchProductPort.findById(orderItem.getProduct().getId())));
        return order;
    }
}
