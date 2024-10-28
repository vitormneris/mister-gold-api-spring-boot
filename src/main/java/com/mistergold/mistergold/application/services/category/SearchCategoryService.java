package com.mistergold.mistergold.application.services.category;

import com.mistergold.mistergold.application.domain.category.Category;
import com.mistergold.mistergold.application.ports.in.category.SearchCategoryUseCase;
import com.mistergold.mistergold.application.ports.out.category.SearchCategoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchCategoryService implements SearchCategoryUseCase {
    private final SearchCategoryPort searchCategoryPort;

    @Override
    public Category findById(String id) {
        return searchCategoryPort.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return searchCategoryPort.findAll();
    }
}
