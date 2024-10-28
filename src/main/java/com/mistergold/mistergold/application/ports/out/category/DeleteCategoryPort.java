package com.mistergold.mistergold.application.ports.out.category;

import com.mistergold.mistergold.application.domain.category.Category;

public interface DeleteCategoryPort {
    void delete(String id);
    Category inactivate(String id);
}
