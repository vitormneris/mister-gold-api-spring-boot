package com.mistergold.mistergold.application.ports.in.product;

import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.product.Product;

public interface SearchProductUseCase {
    PageResponse<Product> findByPagination(Boolean isActive, Integer page, Integer pageSize, String name);
    Product findById(String id);
}
