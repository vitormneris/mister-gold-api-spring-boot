package com.mistergold.mistergold.adapters.persistence.services.category;

import com.mistergold.mistergold.adapters.persistence.mappers.CategoryPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.CategoryRepository;
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
    private final CategoryPersistenceMapper mapper;

    @Override
    public Category findById(String id) {
        return mapper.mapToDomain(categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0006)));
    }

    @Override
    public List<Category> findAll() {
        return mapper.mapListToDomain(categoryRepository.findAll());
    }
}
