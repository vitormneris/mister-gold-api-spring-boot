package com.mistergold.mistergold.application.services.category;

import com.mistergold.mistergold.application.domain.category.Category;
import com.mistergold.mistergold.application.ports.in.category.DeleteCategoryUseCase;
import com.mistergold.mistergold.application.ports.out.category.DeleteCategoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCategoryService implements DeleteCategoryUseCase {
    private final DeleteCategoryPort deleteCategoryPort;

    @Override
    public void delete(String id) {
        deleteCategoryPort.delete(id);
    }

    @Override
    public Category inactivate(String id) {
        return deleteCategoryPort.inactivate(id);
    }
}
