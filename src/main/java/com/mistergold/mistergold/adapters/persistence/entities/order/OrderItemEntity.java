package com.mistergold.mistergold.adapters.persistence.entities.order;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntity {
    @Field(name = "quantidade")
    private Integer quantity;
    @Field(name = "preco")
    private Double price;
    @Field(name = "produto_id")
    private String productId;
}
