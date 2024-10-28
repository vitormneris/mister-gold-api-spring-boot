package com.mistergold.mistergold.application.ports.out.product;

import com.mistergold.mistergold.application.domain.product.Product;

public interface SaveProductPort {
    Product save(Product product);
}
