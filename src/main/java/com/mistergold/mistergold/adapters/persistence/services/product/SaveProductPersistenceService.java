package com.mistergold.mistergold.adapters.persistence.services.product;

import com.mistergold.mistergold.adapters.persistence.mappers.ProductPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.ProductRepository;
import com.mistergold.mistergold.application.domain.product.Product;
import com.mistergold.mistergold.application.ports.out.product.SaveProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveProductPersistenceService implements SaveProductPort {
    private final ProductRepository productRepository;
    private final ProductPersistenceMapper mapper;

    @Override
    public Product save(Product product) {
        return mapper.mapToDomain(productRepository.save(mapper.mapToEntity(product)));
    }
}
