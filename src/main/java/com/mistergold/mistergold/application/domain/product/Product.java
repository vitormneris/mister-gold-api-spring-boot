package com.mistergold.mistergold.application.domain.product;

import com.mistergold.mistergold.application.domain.InfoActivation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product {
    private String id;
    private String name;
    private String imageUrl;
    private String description;
    private Double price;
    private Integer quantity;
    private InfoActivation infoActivation;
}
