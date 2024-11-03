package com.mistergold.mistergold.application.services.product;

import com.mistergold.mistergold.application.domain.product.Product;
import com.mistergold.mistergold.application.ports.in.product.SearchProductUseCase;
import com.mistergold.mistergold.application.ports.out.product.SearchProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchProductService implements SearchProductUseCase {
    private final SearchProductPort searchProductPort;

    @Override
    public Product findById(String id) {
        return searchProductPort.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return searchProductPort.findAll();
    }
}
