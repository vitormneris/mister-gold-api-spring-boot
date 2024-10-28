package com.mistergold.mistergold.application.ports.out.category;

import com.mistergold.mistergold.application.domain.category.Category;

public interface SaveCategoryPort {
    Category save(Category category);
}
