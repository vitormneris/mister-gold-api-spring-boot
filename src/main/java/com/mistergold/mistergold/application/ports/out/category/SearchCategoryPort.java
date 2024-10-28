package com.mistergold.mistergold.application.ports.out.category;

import com.mistergold.mistergold.application.domain.category.Category;

import java.util.List;

public interface SearchCategoryPort {
    List<Category> findAll();
    Category findById(String id);
}
