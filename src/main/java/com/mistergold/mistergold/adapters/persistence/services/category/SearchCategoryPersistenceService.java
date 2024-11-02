package com.mistergold.mistergold.adapters.persistence.services.category;

import com.mistergold.mistergold.adapters.persistence.entities.product.ProductEntity;
import com.mistergold.mistergold.adapters.persistence.mappers.CategoryPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.mappers.ProductPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.CategoryRepository;
import com.mistergold.mistergold.adapters.persistence.repositories.ProductRepository;
import com.mistergold.mistergold.application.domain.category.Category;
import com.mistergold.mistergold.application.ports.out.category.SearchCategoryPort;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchCategoryPersistenceService implements SearchCategoryPort {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductPersistenceMapper productMapper;
    private final CategoryPersistenceMapper categoryMapper;

    @Override
    public Category findById(String id) {
        Category category = categoryMapper.mapToDomain(categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0006)));
        List<ProductEntity> products = category.getProducts().stream()
                .map(product -> productRepository.findById(product.getId()).get()).toList();

        category.setProducts(productMapper.mapListToDomain(products));
        return category;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = categoryMapper.mapListToDomain(categoryRepository.findAll());

        categories.forEach(category -> category.setProducts(category.getProducts().stream()
                .map(product -> productMapper.mapToDomain(productRepository.findById(product.getId()).get())).toList()));

        return categories;
    }
}
