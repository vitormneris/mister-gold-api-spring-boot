package com.mistergold.mistergold.adapters.persistence.services.product;

import com.mistergold.mistergold.adapters.persistence.entities.category.CategoryEntity;
import com.mistergold.mistergold.adapters.persistence.mappers.CategoryPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.mappers.ProductPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.CategoryRepository;
import com.mistergold.mistergold.adapters.persistence.repositories.ProductRepository;
import com.mistergold.mistergold.application.domain.product.Product;
import com.mistergold.mistergold.application.ports.out.product.SaveProductPort;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaveProductPersistenceService implements SaveProductPort {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductPersistenceMapper productMapper;
    private final CategoryPersistenceMapper categoryMapper;

    @Override
    public Product save(Product product) {
        Set<CategoryEntity> categories = product.getCategories().stream()
                .map(category -> categoryRepository.findById(category.getId())
                .orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0006))).collect(Collectors.toSet());

        product.setCategories(categoryMapper.mapListToDomain(categories));
        product.setId(productRepository.save(productMapper.mapToEntity(product)).getId());

        categories.forEach(category -> category.getProductsId().add(product.getId()));
        categoryRepository.saveAll(categories);
        return product;
    }
}
