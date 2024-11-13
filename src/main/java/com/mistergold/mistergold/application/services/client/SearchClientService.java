package com.mistergold.mistergold.application.services.client;

import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.client.Client;
import com.mistergold.mistergold.application.domain.order.Order;
import com.mistergold.mistergold.application.ports.in.client.SearchClientUseCase;
import com.mistergold.mistergold.application.ports.out.client.SearchClientPort;

import com.mistergold.mistergold.application.ports.out.order.SearchOrderPort;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchClientService implements SearchClientUseCase {
    private final SearchClientPort searchClientPort;
    private final SearchOrderPort searchOrderPort;

    @Override
    public PageResponse<Client> findByPagination(Boolean isActive, Integer page, Integer pageSize, String name) {
        return  searchClientPort.findByPagination(isActive, page, pageSize, name);
    }

    @Override
    public Client findById(String id) {
        Client client = searchClientPort.findById(id);
        Set<Order> orders = new HashSet<>();
        client.getOrder().forEach(order -> orders.add(searchOrderPort.findById(order.getId())));
        client.setOrder(orders);
        return client;
    }

    @Override
    public Client findByEmail(String email) {
        return searchClientPort.findByEmail(email);
    }
}
