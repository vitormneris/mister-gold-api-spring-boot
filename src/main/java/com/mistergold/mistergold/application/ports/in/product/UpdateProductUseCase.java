package com.mistergold.mistergold.application.ports.in.product;

import com.mistergold.mistergold.application.domain.product.Product;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateProductUseCase {
    Product update(Product productNew, MultipartFile file, String id);
}
