package com.mistergold.mistergold.adapters.web.in.order.dto;

import com.mistergold.mistergold.adapters.web.in.product.dto.ProductDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record OrderItemDTO(
        @NotNull(message = "O campo 'Quantidade' não deve ser nulo.")
        Integer quantity,
        Double price,
        @NotNull(message = "O campo 'Produto' não deve ser nulo.")
        ProductDTO product
) {
}
