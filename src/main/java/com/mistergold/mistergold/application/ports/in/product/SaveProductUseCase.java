package com.mistergold.mistergold.application.ports.in.product;

import com.mistergold.mistergold.application.domain.product.Product;

public interface SaveProductUseCase {
    Product save(Product product);
}
