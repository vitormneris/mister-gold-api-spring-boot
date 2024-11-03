package com.mistergold.mistergold.adapters.persistence.services.order;

import com.mistergold.mistergold.adapters.persistence.mappers.OrderPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.OrderRepository;
import com.mistergold.mistergold.application.domain.order.Order;
import com.mistergold.mistergold.application.ports.out.order.SaveOrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveOrderPersistenceService implements SaveOrderPort {
    private final OrderRepository orderRepository;
    private final OrderPersistenceMapper orderMapper;

    @Override
    public Order save(Order order) {
        order.setId(orderRepository.save(orderMapper.mapToEntity(order)).getId());
        return order;
    }
}
