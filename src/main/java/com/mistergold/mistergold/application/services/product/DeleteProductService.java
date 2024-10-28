package com.mistergold.mistergold.application.services.product;

import com.mistergold.mistergold.application.domain.product.Product;
import com.mistergold.mistergold.application.ports.in.product.DeleteProductUseCase;
import com.mistergold.mistergold.application.ports.out.product.DeleteProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProductService implements DeleteProductUseCase {
    private final DeleteProductPort deleteProductPort;

    @Override
    public void delete(String id) {
        deleteProductPort.delete(id);
    }

    @Override
    public Product inactivate(String id) {
        return deleteProductPort.inactivate(id);
    }
}
