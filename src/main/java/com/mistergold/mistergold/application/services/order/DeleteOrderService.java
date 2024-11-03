package com.mistergold.mistergold.application.services.order;

import com.mistergold.mistergold.application.domain.order.Order;
import com.mistergold.mistergold.application.ports.in.order.DeleteOrderUseCase;
import com.mistergold.mistergold.application.ports.out.client.SearchClientPort;
import com.mistergold.mistergold.application.ports.out.order.DeleteOrderPort;
import com.mistergold.mistergold.application.ports.out.product.SearchProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteOrderService implements DeleteOrderUseCase {
    private final DeleteOrderPort deleteOrderPort;
    private final SearchClientPort searchClientPort;
    private final SearchProductPort searchProductPort;

    @Override
    public void delete(String id) {
        deleteOrderPort.delete(id);
    }

    @Override
    public Order inactivate(String id) {
        Order order = deleteOrderPort.inactivate(id);
        order.setClient(searchClientPort.findById(order.getClient().getId()));
        order.getItems().forEach(orderItem -> orderItem.setProduct(searchProductPort.findById(orderItem.getProduct().getId())));
        return order;
    }
}
