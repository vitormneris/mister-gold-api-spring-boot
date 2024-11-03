package com.mistergold.mistergold.adapters.persistence.services.order;

import com.mistergold.mistergold.adapters.persistence.entities.order.OrderEntity;
import com.mistergold.mistergold.adapters.persistence.mappers.OrderPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.OrderRepository;
import com.mistergold.mistergold.application.domain.order.Order;
import com.mistergold.mistergold.application.ports.out.order.DeleteOrderPort;
import com.mistergold.mistergold.configuration.web.advice.exception.DataIntegratyViolationException;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class DeleteOrderPersistenceService implements DeleteOrderPort {
    private final OrderRepository orderRepository;
    private final OrderPersistenceMapper mapper;

    @Override
    public void delete(String id) {
        orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0009));
        orderRepository.deleteById(id);
    }


    @Override
    public Order inactivate(String id) {
        OrderEntity order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0009));
        if (order.getInfoActivation().getIsActive()) {
            order.getInfoActivation().setIsActive(false);
            order.getInfoActivation().setDeactivationDate(Instant.now());
        } else throw new DataIntegratyViolationException(RunErrorEnum.ERR0004);
        return mapper.mapToDomain(orderRepository.save(order));
    }
}
