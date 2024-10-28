package com.mistergold.mistergold.application.ports.in.product;

import com.mistergold.mistergold.application.domain.product.Product;

public interface UpdateProductUseCase {
    Product update(Product product, String id);
}
