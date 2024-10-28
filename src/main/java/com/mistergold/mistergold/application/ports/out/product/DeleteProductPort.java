package com.mistergold.mistergold.application.ports.out.product;

import com.mistergold.mistergold.application.domain.product.Product;

public interface DeleteProductPort {
    void delete(String id);
    Product inactivate(String id);
}
