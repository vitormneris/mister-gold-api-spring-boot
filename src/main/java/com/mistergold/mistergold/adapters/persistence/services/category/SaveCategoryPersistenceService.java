package com.mistergold.mistergold.adapters.persistence.services.category;

import com.mistergold.mistergold.adapters.persistence.mappers.CategoryPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.CategoryRepository;
import com.mistergold.mistergold.application.domain.category.Category;
import com.mistergold.mistergold.application.ports.out.category.SaveCategoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveCategoryPersistenceService implements SaveCategoryPort {
    private final CategoryRepository categoryRepository;
    private final CategoryPersistenceMapper categoryMapper;

    @Override
    public Category save(Category category) {
        return categoryMapper.mapToDomain(categoryRepository.save(categoryMapper.mapToEntity(category)));
    }
}
