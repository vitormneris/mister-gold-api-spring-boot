package com.mistergold.mistergold.application.ports.out.product;

import com.mistergold.mistergold.application.domain.product.Product;

import java.util.List;

public interface SearchProductPort {
    List<Product> findAll();
    Product findById(String id);
}
