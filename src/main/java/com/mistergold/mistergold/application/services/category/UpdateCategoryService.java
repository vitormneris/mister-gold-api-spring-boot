package com.mistergold.mistergold.application.services.category;

import com.mistergold.mistergold.application.domain.category.Category;
import com.mistergold.mistergold.application.ports.in.category.UpdateCategoryUseCase;
import com.mistergold.mistergold.application.ports.out.category.UpdateCategoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCategoryService implements UpdateCategoryUseCase {
    private final UpdateCategoryPort updateCategoryPort;

    @Override
    public Category update(Category categoryNew, String id) {
        categoryNew.setId(id);

        return updateCategoryPort.update(categoryNew, id);
    }
}
