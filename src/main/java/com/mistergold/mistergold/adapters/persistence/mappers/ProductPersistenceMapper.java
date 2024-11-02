package com.mistergold.mistergold.adapters.persistence.mappers;

import com.mistergold.mistergold.adapters.persistence.entities.InfoActivationEntity;
import com.mistergold.mistergold.adapters.persistence.entities.product.ProductEntity;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.category.Category;
import com.mistergold.mistergold.application.domain.product.Product;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductPersistenceMapper {
    List<Product> mapListToDomain(List<ProductEntity> entities);

    default ProductEntity mapToEntity(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .imageUrl(product.getImageUrl())
                .description(product.getDescription())
                .color(product.getColor())
                .size(product.getSize())
                .material(product.getMaterial())
                .weight(product.getWeight())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .categoriesId(product.getCategories().stream().map(Category::getId).collect(Collectors.toSet()))
                .infoActivation(product.getInfoActivation())
                .build();
    }

    default Product mapToDomain(ProductEntity productEntity) {
        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .imageUrl(productEntity.getImageUrl())
                .description(productEntity.getDescription())
                .color(productEntity.getColor())
                .size(productEntity.getSize())
                .material(productEntity.getMaterial())
                .weight(productEntity.getWeight())
                .quantity(productEntity.getQuantity())
                .price(productEntity.getPrice())
                .categories(productEntity.getCategoriesId().stream().map((categoryId) -> Category.builder().id(categoryId).build()).toList())
                .infoActivation(productEntity.getInfoActivation())
                .build();
    }

    InfoActivation mapToDomain(InfoActivationEntity infoActivationEntity);
}
