package com.mistergold.mistergold.adapters.persistence.services.product;

import com.mistergold.mistergold.adapters.persistence.entities.category.CategoryEntity;
import com.mistergold.mistergold.adapters.persistence.entities.product.ProductEntity;
import com.mistergold.mistergold.adapters.persistence.mappers.CategoryPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.CategoryRepository;
import com.mistergold.mistergold.adapters.persistence.repositories.ProductRepository;
import com.mistergold.mistergold.application.domain.category.Category;
import com.mistergold.mistergold.application.domain.product.Product;
import com.mistergold.mistergold.application.ports.out.product.UpdateProductPort;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdateProductPersistenceService implements UpdateProductPort {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryPersistenceMapper categoryMapper;

    @Override
    public Product update(Product productNew, String id) {
        Set<CategoryEntity> categories = new HashSet<>();
        if (productNew.getCategories() != null)
            categories = productNew.getCategories().stream()
                    .map(category -> categoryRepository.findById(category.getId())
                            .orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0006))).collect(Collectors.toSet());

        productNew.setCategories(categoryMapper.mapListToDomain(categories));

        ProductEntity productOld = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0005));


        categoryRepository.findAllById(productOld.getCategoriesId()).forEach(category -> {
            category.setProductsId(category.getProductsId().stream().filter(productId -> !productId.equals(productOld.getId())).collect(Collectors.toSet()));
            categoryRepository.save(category);
        });

        categories.forEach(category -> category.getProductsId().add(productOld.getId()));
        categoryRepository.saveAll(categories);


        productOld.setName(productNew.getName() == null ? productOld.getName() : productNew.getName());
        productOld.setImageUrl(productNew.getImageUrl() == null ? productOld.getImageUrl() : productNew.getImageUrl());
        productOld.setDescription(productNew.getDescription() == null ? productOld.getDescription() : productNew.getDescription());
        productOld.setQuantity(productNew.getQuantity() == null ? productOld.getQuantity() : productNew.getQuantity());
        productOld.setCategoriesId(productNew.getCategories() == null ? productOld.getCategoriesId() : productNew.getCategories().stream().map(Category::getId).collect(Collectors.toSet()));
        productOld.setPrice(productNew.getPrice() == null ? productOld.getPrice() : productNew.getPrice());

        productNew.setId(productRepository.save(productOld).getId());
        productNew.setInfoActivation(categoryMapper.mapToDomain(productOld.getInfoActivation()));
        return productNew;
    }
}
