package com.mistergold.mistergold.application.domain.product;

import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.category.Category;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String id;
    private String name;
    private String imageUrl;
    private Double size;
    private String color;
    private Double weight;
    private String material;
    private String description;
    private Double price;
    private Integer quantity;
    private Set<Category> categories = new HashSet<>();
    private InfoActivation infoActivation;
}
