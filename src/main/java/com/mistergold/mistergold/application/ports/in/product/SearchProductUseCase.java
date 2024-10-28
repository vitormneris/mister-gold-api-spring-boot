package com.mistergold.mistergold.application.ports.in.product;

import com.mistergold.mistergold.application.domain.product.Product;

import java.util.List;

public interface SearchProductUseCase {
    List<Product> findAll();
    Product findById(String id);
}
