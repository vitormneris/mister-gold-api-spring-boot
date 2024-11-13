package com.mistergold.mistergold.application.services.category;

import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.category.Category;
import com.mistergold.mistergold.application.ports.in.category.SearchCategoryUseCase;
import com.mistergold.mistergold.application.ports.out.category.SearchCategoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchCategoryService implements SearchCategoryUseCase {
    private final SearchCategoryPort searchCategoryPort;

    @Override
    public PageResponse<Category> findByPagination(Boolean isActive, Integer page, Integer pageSize, String name) {
        return searchCategoryPort.findByPagination(isActive, page, pageSize, name);
    }

    @Override
    public Category findById(String id) {
        return searchCategoryPort.findById(id);
    }


}
