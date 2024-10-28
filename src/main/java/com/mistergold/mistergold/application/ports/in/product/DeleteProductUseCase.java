package com.mistergold.mistergold.application.ports.in.product;

import com.mistergold.mistergold.application.domain.product.Product;

public interface DeleteProductUseCase {
    void delete(String id);
    Product inactivate(String id);
}
