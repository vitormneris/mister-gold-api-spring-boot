package com.mistergold.mistergold.application.domain.order;

import com.mistergold.mistergold.application.domain.product.Product;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Integer quantity;
    private Double price;
    private Product product;
}
