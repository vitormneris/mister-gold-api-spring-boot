package com.mistergold.mistergold.adapters.persistence.services.product;

import com.mistergold.mistergold.adapters.persistence.entities.product.ProductEntity;
import com.mistergold.mistergold.adapters.persistence.mappers.ProductPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.ProductRepository;
import com.mistergold.mistergold.application.domain.product.Product;
import com.mistergold.mistergold.application.ports.out.product.UpdateProductPort;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateProductPersistenceService implements UpdateProductPort {
    private final ProductRepository productRepository;
    private final ProductPersistenceMapper mapper;

    @Override
    public Product update(Product productNew, String id) {
        ProductEntity productOld = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0005));

        productOld.setName(productNew.getName() == null ? productOld.getName() : productNew.getName());
        productOld.setImageUrl(productNew.getImageUrl() == null ? productOld.getImageUrl() : productNew.getImageUrl());
        productOld.setDescription(productNew.getDescription() == null ? productOld.getDescription() : productNew.getDescription());
        productOld.setQuantity(productNew.getQuantity() == null ? productOld.getQuantity() : productNew.getQuantity());
        productOld.setPrice(productNew.getPrice() == null ? productOld.getPrice() : productNew.getPrice());

        return mapper.mapToDomain(productRepository.save(productOld));
    }
}
