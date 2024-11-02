package com.mistergold.mistergold.application.services.category;

import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.category.Category;
import com.mistergold.mistergold.application.domain.product.Product;
import com.mistergold.mistergold.application.ports.in.category.SaveCategoryUseCase;
import com.mistergold.mistergold.application.ports.in.product.SaveProductUseCase;
import com.mistergold.mistergold.application.ports.out.category.SaveCategoryPort;
import com.mistergold.mistergold.application.ports.out.product.SaveProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class SaveCategoryService implements SaveCategoryUseCase {
    private final SaveCategoryPort saveCategoryPort;

    @Override
    public Category save(Category category) {
        category.setId(null);


        InfoActivation infoActivation = InfoActivation.builder()
            .creationDate(Instant.now())
            .isActive(true)
            .build();

        category.setInfoActivation(infoActivation);

        return saveCategoryPort.save(category);
    }
}
