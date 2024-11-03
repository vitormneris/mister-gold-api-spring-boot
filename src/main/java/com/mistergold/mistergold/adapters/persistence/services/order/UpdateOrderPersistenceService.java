package com.mistergold.mistergold.adapters.persistence.services.order;

import com.mistergold.mistergold.adapters.persistence.entities.order.OrderEntity;
import com.mistergold.mistergold.adapters.persistence.mappers.OrderPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.OrderRepository;
import com.mistergold.mistergold.application.domain.order.Order;
import com.mistergold.mistergold.application.ports.out.order.UpdateOrderPort;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateOrderPersistenceService implements UpdateOrderPort {
    private final OrderRepository orderRepository;
    private final OrderPersistenceMapper orderMapper;

    @Override
    public Order update(Order orderNew, String id) {
        OrderEntity orderOld = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0009));
        orderOld.setOrderStatus(orderNew.getOrderStatus() == null ? orderOld.getOrderStatus() : orderNew.getOrderStatus().getCode());
        return orderMapper.mapToDomain(orderRepository.save(orderOld));
    }
}
