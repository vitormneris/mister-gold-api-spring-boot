package com.mistergold.mistergold.adapters.web.in.product.mapper;

import com.mistergold.mistergold.adapters.web.PageResponseDTO;
import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import com.mistergold.mistergold.adapters.web.in.category.dto.CategoryDTO;
import com.mistergold.mistergold.adapters.web.in.product.dto.ProductDTO;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.category.Category;
import com.mistergold.mistergold.application.domain.product.Product;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductWebMapper {
    List<ProductDTO> mapToListDTO(List<Product> products);

    default ProductDTO mapToDTO(Product product) {
        Set<CategoryDTO> categoryDTOS = product.getCategories().stream().map(category -> CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .imageUrl(category.getImageUrl())
                .infoActivation(mapToDTO(category.getInfoActivation()))
                .build()).collect(Collectors.toSet());

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .price(product.getPrice())
                .quantity(product.getQuantity())
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
                .quantity(productDTO.quantity())
                .categories(productDTO.categories().stream().map((categoryDTO) -> Category.builder().id(categoryDTO.id()).build()).collect(Collectors.toSet()))
                .infoActivation(mapToDomain(productDTO.infoActivation()))
                .build();
    }

    PageResponseDTO<ProductDTO> mapToPageResponseDto(PageResponse<Product> pageResponse);
    InfoActivationDTO mapToDTO(InfoActivation infoActivation);
    InfoActivation mapToDomain(InfoActivationDTO infoActivation);
}
