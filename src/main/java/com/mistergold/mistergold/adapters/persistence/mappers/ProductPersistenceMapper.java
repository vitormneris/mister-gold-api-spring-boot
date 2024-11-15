package com.mistergold.mistergold.adapters.persistence.mappers;

import com.mistergold.mistergold.adapters.persistence.entities.InfoActivationEntity;
import com.mistergold.mistergold.adapters.persistence.entities.product.ProductEntity;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.category.Category;
import com.mistergold.mistergold.application.domain.product.Product;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductPersistenceMapper {
    Set<Product> mapListToDomain(Set<ProductEntity> entities);

    default ProductEntity mapToEntity(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .imageUrl(product.getImageUrl())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .categoriesId(product.getCategories().stream().map(Category::getId).collect(Collectors.toSet()))
                .infoActivation(mapToEntity(product.getInfoActivation()))
                .build();
    }

    default Product mapToDomain(ProductEntity productEntity) {
        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .imageUrl(productEntity.getImageUrl())
                .description(productEntity.getDescription())
                .quantity(productEntity.getQuantity())
                .price(productEntity.getPrice())
                .categories(productEntity.getCategoriesId().stream().map((categoryId) -> Category.builder().id(categoryId).build()).collect(Collectors.toSet()))
                .infoActivation(mapToDomain(productEntity.getInfoActivation()))
                .build();
    }

    default PageResponse<Product> mapToPageResponseDomain(Page<ProductEntity> productEntities) {
        int previousPage = productEntities.hasPrevious() ? productEntities.getNumber() - 1 : productEntities.getNumber();
        int nextPage = productEntities.hasNext() ? productEntities.getNumber() + 1 : productEntities.getNumber();

        Set<Product> products = productEntities.getContent().stream().map(this::mapToDomain).collect(Collectors.toSet());

        return PageResponse.<Product>builder()
                .pageSize(productEntities.getNumberOfElements())
                .totalElements(productEntities.getTotalElements())
                .currentPage(productEntities.getNumber())
                .previousPage(previousPage)
                .nextPage(nextPage)
                .content(products)
                .totalPages(productEntities.getTotalPages())
                .build();
    }

    InfoActivationEntity mapToEntity(InfoActivation infoActivation);
    InfoActivation mapToDomain(InfoActivationEntity infoActivationEntity);
}
