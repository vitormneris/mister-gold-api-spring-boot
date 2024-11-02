package com.mistergold.mistergold.adapters.web.in.product.mapper;

import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import com.mistergold.mistergold.adapters.web.in.category.dto.CategoryDTO;
import com.mistergold.mistergold.adapters.web.in.product.dto.ProductDTO;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.category.Category;
import com.mistergold.mistergold.application.domain.product.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductWebMapper {
    List<ProductDTO> mapToListDTO(List<Product> products);

    default ProductDTO mapToDTO(Product product) {
        List<CategoryDTO> categoryDTOS = product.getCategories().stream().map(category -> CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .imageUrl(category.getImageUrl())
                .infoActivation(mapToDTO(category.getInfoActivation()))
                .build()).toList();

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .price(product.getPrice())
                .size(product.getSize())
                .color(product.getColor())
                .weight(product.getWeight())
                .quantity(product.getQuantity())
                .material(product.getMaterial())
                .categories(categoryDTOS)
                .infoActivation(mapToDTO(product.getInfoActivation()))
                .build();
    }

    default Product mapToDomain(ProductDTO productDTO) {
        return Product.builder()
                .id(productDTO.id())
                .name(productDTO.name())
                .description(productDTO.description())
                .imageUrl(productDTO.imageUrl())
                .price(productDTO.price())
                .size(productDTO.size())
                .color(productDTO.color())
                .weight(productDTO.weight())
                .quantity(productDTO.quantity())
                .material(productDTO.material())
                .categories(productDTO.categories().stream().map((categoryDTO) -> Category.builder().id(categoryDTO.id()).build()).toList())
                .infoActivation(mapToDomain(productDTO.infoActivation()))
                .build();
    }

    InfoActivationDTO mapToDTO(InfoActivation infoActivation);
    InfoActivation mapToDomain(InfoActivationDTO infoActivation);
}
