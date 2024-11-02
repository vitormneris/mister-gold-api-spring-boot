package com.mistergold.mistergold.adapters.persistence.mappers;

import com.mistergold.mistergold.adapters.persistence.entities.InfoActivationEntity;
import com.mistergold.mistergold.adapters.persistence.entities.category.CategoryEntity;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.category.Category;
import com.mistergold.mistergold.application.domain.product.Product;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CategoryPersistenceMapper {
    List<Category> mapListToDomain(List<CategoryEntity> entities);

    default CategoryEntity mapToEntity(Category category) {
        return CategoryEntity.builder()
            .id(category.getId())
            .name(category.getName())
            .description(category.getDescription())
            .imageUrl(category.getImageUrl())
            .productsId(category.getProducts() == null ? new HashSet<>() : category.getProducts().stream().map(Product::getId).collect(Collectors.toSet()))
            .infoActivation(category.getInfoActivation())
            .build();
    }

    default Category mapToDomain(CategoryEntity categoryEntity) {
        return Category.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .description(categoryEntity.getDescription())
                .imageUrl(categoryEntity.getImageUrl())
                .products(categoryEntity.getProductsId() == null ? new ArrayList<>() : categoryEntity.getProductsId().stream().map((productId) -> Product.builder().id(productId).build()).toList())
                .infoActivation(categoryEntity.getInfoActivation())
                .build();
    }

    InfoActivation mapToDomain(InfoActivationEntity infoActivationEntity);
}
