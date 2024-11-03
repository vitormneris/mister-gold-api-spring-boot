package com.mistergold.mistergold.adapters.persistence.services.order;

import com.mistergold.mistergold.adapters.persistence.mappers.OrderPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.OrderRepository;
import com.mistergold.mistergold.application.domain.order.Order;
import com.mistergold.mistergold.application.ports.out.order.SearchOrderPort;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchOrderPersistenceService implements SearchOrderPort {
    private final OrderRepository orderRepository;
    private final OrderPersistenceMapper orderMapper;


    @Override
    public Order findById(String id) {
        return orderMapper.mapToDomain(orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0009)));
    }

    @Override
    public List<Order> findAll() {
        return orderMapper.mapListToDomain(orderRepository.findAll());
    }
}
