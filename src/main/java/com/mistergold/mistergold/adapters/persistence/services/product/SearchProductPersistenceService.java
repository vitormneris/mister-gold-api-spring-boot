package com.mistergold.mistergold.adapters.persistence.services.product;

import com.mistergold.mistergold.adapters.persistence.entities.category.CategoryEntity;
import com.mistergold.mistergold.adapters.persistence.mappers.CategoryPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.mappers.ProductPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.CategoryRepository;
import com.mistergold.mistergold.adapters.persistence.repositories.ProductRepository;
import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.product.Product;
import com.mistergold.mistergold.application.ports.out.product.SearchProductPort;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchProductPersistenceService implements SearchProductPort {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductPersistenceMapper productMapper;
    private final CategoryPersistenceMapper categoryMapper;

    @Override
    public Product findById(String id) {
        Product product = productMapper.mapToDomain(productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0005)));
        Set<CategoryEntity> categories = product.getCategories().stream()
                .map(category -> categoryRepository.findById(category.getId()).get()).collect(Collectors.toSet());

        product.setCategories(categoryMapper.mapListToDomain(categories));
        return product;
    }

    @Override
    public PageResponse<Product> findByPagination(Boolean isActive, Integer page, Integer pageSize, String name) {
        PageResponse<Product> pageResponse = productMapper.mapToPageResponseDomain(
                productRepository.findByPagination(isActive, PageRequest.of(page, pageSize), (name == null) ? "" : name));

        pageResponse.getContent().forEach(product -> product.setCategories(product.getCategories().stream()
                .map(category -> categoryMapper.mapToDomain(categoryRepository.findById(category.getId()).get())).collect(Collectors.toSet())));

        return pageResponse;
    }
}
