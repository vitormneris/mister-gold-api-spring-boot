package com.mistergold.mistergold.application.domain.category;

import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.product.Product;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private String id;
    private String name;
    private String imageUrl;
    private String description;
    private List<Product> products = new ArrayList<>();
    private InfoActivation infoActivation;
}
