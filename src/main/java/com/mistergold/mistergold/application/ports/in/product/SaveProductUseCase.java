package com.mistergold.mistergold.application.ports.in.product;

import com.mistergold.mistergold.application.domain.product.Product;
import org.springframework.web.multipart.MultipartFile;

public interface SaveProductUseCase {
    Product save(Product product, MultipartFile file);
}
