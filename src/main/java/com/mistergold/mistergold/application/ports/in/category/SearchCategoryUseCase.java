package com.mistergold.mistergold.application.ports.in.category;

import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.category.Category;

public interface SearchCategoryUseCase {
    PageResponse<Category> findByPagination(Boolean isActive, Integer page, Integer pageSize, String name);
    Category findById(String id);
}
