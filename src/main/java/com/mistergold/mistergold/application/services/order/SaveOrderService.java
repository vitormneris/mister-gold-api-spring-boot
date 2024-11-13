package com.mistergold.mistergold.application.services.order;

import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.client.Client;
import com.mistergold.mistergold.application.domain.order.Order;
import com.mistergold.mistergold.application.domain.product.Product;
import com.mistergold.mistergold.application.ports.in.order.SaveOrderUseCase;
import com.mistergold.mistergold.application.ports.out.client.SaveClientPort;
import com.mistergold.mistergold.application.ports.out.client.SearchClientPort;
import com.mistergold.mistergold.application.ports.out.client.UpdateClientPort;
import com.mistergold.mistergold.application.ports.out.order.SaveOrderPort;
import com.mistergold.mistergold.application.ports.out.product.SearchProductPort;
import com.mistergold.mistergold.configuration.web.enums.OrderStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class SaveOrderService implements SaveOrderUseCase {
    private final SearchClientPort searchClientPort;
    private final SearchProductPort searchProductPort;
    private final UpdateClientPort updateClientPort;
    private final SaveOrderPort saveOrderPort;

    @Override
    public Order save(Order order) {
        order.setId(null);
        order.setMoment(Instant.now());
        order.setOrderStatus(OrderStatusEnum.WAITING_PAYMENT);
        Client client = searchClientPort.findById(order.getClient().getId());
        order.setClient(client);

        order.getItems().forEach(orderItem -> {
            Product product = searchProductPort.findById(orderItem.getProduct().getId());
            orderItem.setProduct(product);
            orderItem.setPrice(product.getPrice());
            orderItem.setQuantity(orderItem.getQuantity());
        });

        InfoActivation infoActivation = InfoActivation.builder()
            .creationDate(Instant.now())
            .isActive(true)
            .build();

        order.setInfoActivation(infoActivation);

        Order orderSaved = saveOrderPort.save(order);
        if (client.getOrder() == null) client.setOrder(new HashSet<>());
        client.getOrder().add(orderSaved);
        updateClientPort.update(client, order.getClient().getId());
        return orderSaved;
    }
}
