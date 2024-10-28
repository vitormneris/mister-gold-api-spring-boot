package com.mistergold.mistergold.application.services.product;

import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.product.Product;
import com.mistergold.mistergold.application.ports.in.product.SaveProductUseCase;
import com.mistergold.mistergold.application.ports.out.product.SaveProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class SaveProductService implements SaveProductUseCase {
    private final SaveProductPort saveProductPort;

    @Override
    public Product save(Product product) {
        InfoActivation infoActivation = InfoActivation.builder()
            .creationDate(Instant.now())
            .isActive(true)
            .build();

        product.setInfoActivation(infoActivation);

        return saveProductPort.save(product);
    }
}
