package com.mistergold.mistergold.application.ports.in.category;

import com.mistergold.mistergold.application.domain.category.Category;

public interface SaveCategoryUseCase {
    Category save(Category category);
}
