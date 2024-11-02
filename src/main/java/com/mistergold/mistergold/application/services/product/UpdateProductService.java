package com.mistergold.mistergold.application.services.product;

import com.mistergold.mistergold.application.domain.product.Product;
import com.mistergold.mistergold.application.ports.in.product.UpdateProductUseCase;
import com.mistergold.mistergold.application.ports.out.product.UpdateProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateProductService implements UpdateProductUseCase {
    private final UpdateProductPort updateProductPort;

    @Override
    public Product update(Product productNew, String id) {
        productNew.setId(id);

        return updateProductPort.update(productNew, id);
    }
}
