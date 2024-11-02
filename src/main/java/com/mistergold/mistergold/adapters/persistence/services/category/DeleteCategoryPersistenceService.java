package com.mistergold.mistergold.adapters.persistence.services.category;

import com.mistergold.mistergold.adapters.persistence.entities.category.CategoryEntity;
import com.mistergold.mistergold.adapters.persistence.entities.product.ProductEntity;
import com.mistergold.mistergold.adapters.persistence.mappers.CategoryPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.CategoryRepository;
import com.mistergold.mistergold.adapters.persistence.repositories.ProductRepository;
import com.mistergold.mistergold.application.domain.category.Category;
import com.mistergold.mistergold.application.ports.out.category.DeleteCategoryPort;
import com.mistergold.mistergold.configuration.web.advice.exception.DataIntegratyViolationException;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeleteCategoryPersistenceService implements DeleteCategoryPort {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryPersistenceMapper mapper;

    @Override
    public void delete(String id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0006));
        List<ProductEntity> productEntityList = productRepository.findAllById(categoryEntity.getProductsId());

        productEntityList.forEach(product -> {
            Set<String> categoriesId = product.getCategoriesId().stream().filter(categoryId -> !categoryId.equals(categoryEntity.getId())).collect(Collectors.toSet());
            if (categoriesId.isEmpty()) throw new DataIntegratyViolationException(RunErrorEnum.ERR0008);
            product.setCategoriesId(categoriesId);
        });

        productRepository.saveAll(productEntityList);
        categoryRepository.deleteById(id);
    }


    @Override
    public Category inactivate(String id) {
        CategoryEntity category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0006));
        if (category.getInfoActivation().getIsActive()) {
            category.getInfoActivation().setIsActive(false);
            category.getInfoActivation().setDeactivationDate(Instant.now());
        } else throw new DataIntegratyViolationException(RunErrorEnum.ERR0004);
        return mapper.mapToDomain(categoryRepository.save(category));
    }
}
