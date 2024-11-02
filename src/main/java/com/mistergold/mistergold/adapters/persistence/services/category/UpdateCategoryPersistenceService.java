package com.mistergold.mistergold.adapters.persistence.services.category;

import com.mistergold.mistergold.adapters.persistence.entities.category.CategoryEntity;
import com.mistergold.mistergold.adapters.persistence.mappers.CategoryPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.CategoryRepository;
import com.mistergold.mistergold.application.domain.category.Category;
import com.mistergold.mistergold.application.ports.out.category.UpdateCategoryPort;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCategoryPersistenceService implements UpdateCategoryPort {
    private final CategoryRepository categoryRepository;
    private final CategoryPersistenceMapper categoryMapper;

    @Override
    public Category update(Category categoryNew, String id) {
        CategoryEntity categoryOld = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0006));

        categoryOld.setName(categoryNew.getName() == null ? categoryOld.getName() : categoryNew.getName());
        categoryOld.setImageUrl(categoryNew.getImageUrl() == null ? categoryOld.getImageUrl() : categoryNew.getImageUrl());
        categoryOld.setDescription(categoryNew.getDescription() == null ? categoryOld.getDescription() : categoryNew.getDescription());

        return categoryMapper.mapToDomain(categoryRepository.save(categoryOld));
    }
}
