package com.mistergold.mistergold.application.ports.in.category;

import com.mistergold.mistergold.application.domain.category.Category;

import java.util.List;

public interface SearchCategoryUseCase {
    List<Category> findAll();
    Category findById(String id);
}
