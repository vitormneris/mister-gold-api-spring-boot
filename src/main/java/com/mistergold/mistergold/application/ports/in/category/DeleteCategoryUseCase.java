package com.mistergold.mistergold.application.ports.in.category;

import com.mistergold.mistergold.application.domain.category.Category;

public interface DeleteCategoryUseCase {
    void delete(String id);
    Category inactivate(String id);
}
