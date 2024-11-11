package com.mistergold.mistergold.application.services.product;

import com.mistergold.mistergold.application.domain.product.Product;
import com.mistergold.mistergold.application.ports.in.product.UpdateProductUseCase;
import com.mistergold.mistergold.application.ports.out.product.UpdateProductPort;
import com.mistergold.mistergold.application.ports.out.upload_image.UploadImagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UpdateProductService implements UpdateProductUseCase {
    private final UpdateProductPort updateProductPort;
    private final UploadImagePort uploadImagePort;

    @Override
    public Product update(Product productNew, MultipartFile file, String id) {
        productNew.setId(id);

        if (file != null) {
            productNew.setImageUrl(uploadImagePort.uploadImage(file));
        }

        return updateProductPort.update(productNew, id);
    }
}
