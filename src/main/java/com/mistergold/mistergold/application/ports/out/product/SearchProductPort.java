package com.mistergold.mistergold.application.ports.out.product;

import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.product.Product;

public interface SearchProductPort {
    PageResponse<Product> findByPagination(Boolean isActive, Integer page, Integer pageSize, String name);
    Product findById(String id);
}
