package com.mistergold.mistergold.application.services.category;

import com.mistergold.mistergold.application.domain.category.Category;
import com.mistergold.mistergold.application.ports.in.category.UpdateCategoryUseCase;
import com.mistergold.mistergold.application.ports.out.category.UpdateCategoryPort;
import com.mistergold.mistergold.application.ports.out.upload_image.UploadImagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UpdateCategoryService implements UpdateCategoryUseCase {
    private final UpdateCategoryPort updateCategoryPort;
    private final UploadImagePort uploadImagePort;

    @Override
    public Category update(Category categoryNew, MultipartFile file, String id) {
        categoryNew.setId(id);

        if (file != null) {
            categoryNew.setImageUrl(uploadImagePort.uploadImage(file));
        }
        return updateCategoryPort.update(categoryNew, id);
    }
}
