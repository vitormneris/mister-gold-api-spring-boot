package com.mistergold.mistergold.application.ports.in.category;

import com.mistergold.mistergold.application.domain.category.Category;
import org.springframework.web.multipart.MultipartFile;

public interface SaveCategoryUseCase {
    Category save(Category category, MultipartFile file);
}
