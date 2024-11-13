package com.mistergold.mistergold.adapters.persistence.services.order;

import com.mistergold.mistergold.adapters.persistence.mappers.CategoryPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.mappers.ClientPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.mappers.OrderPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.mappers.ProductPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.CategoryRepository;
import com.mistergold.mistergold.adapters.persistence.repositories.ClientRepository;
import com.mistergold.mistergold.adapters.persistence.repositories.OrderRepository;
import com.mistergold.mistergold.adapters.persistence.repositories.ProductRepository;
import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.order.Order;
import com.mistergold.mistergold.application.domain.product.Product;
import com.mistergold.mistergold.application.ports.out.order.SearchOrderPort;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchOrderPersistenceService implements SearchOrderPort {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final OrderPersistenceMapper orderMapper;
    private final ClientPersistenceMapper clientMapper;
    private final ProductPersistenceMapper productMapper;
    private final CategoryPersistenceMapper categoryMapper;

    @Override
    public Order findById(String id) {
        Order order = orderMapper.mapToDomain(orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0009)));
        order.setClient(clientMapper.mapToDomain(clientRepository.findById(order.getClient().getId()).get()));
        order.getItems().forEach(orderItem -> orderItem.setProduct(
                productMapper.mapToDomain(productRepository.findById(orderItem.getProduct().getId()).get())
        ));
        return order;
    }

    @Override
    public PageResponse<Order> findByPagination(Boolean isActive, Integer page, Integer pageSize, String name) {
        PageResponse<Order> pageResponse = orderMapper.mapToPageResponseDomain(
                orderRepository.findByPagination(isActive, PageRequest.of(page, pageSize), (name == null) ? "" : name));

        pageResponse.getContent().forEach(order -> {
            System.out.println(order.getClient().getId());
            order.setClient(clientMapper.mapToDomain(clientRepository.findById(order.getClient().getId()).get()));
        });

        pageResponse.getContent().forEach(order -> order.getItems().forEach(item ->
        {
            Product product = productMapper.mapToDomain(productRepository.findById(item.getProduct().getId()).get());
            product.getCategories().forEach(
                    category -> categoryMapper.mapToDomain(categoryRepository.findById(category.getId()).get())
            );
            item.setProduct(product);
        }));

        return pageResponse;
    }
}
