package com.mistergold.mistergold.adapters.web.in.category.mapper;

import com.mistergold.mistergold.adapters.web.PageResponseDTO;
import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import com.mistergold.mistergold.adapters.web.in.category.dto.CategoryDTO;
import com.mistergold.mistergold.adapters.web.in.product.dto.ProductDTO;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.category.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryWebMapper {
    List<CategoryDTO> mapToListDTO(List<Category> categories);

    default CategoryDTO mapToDTO(Category category) {
        List<ProductDTO> productDTOS = category.getProducts().stream().map(product -> ProductDTO.builder()
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
                .infoActivation(mapToDTO(product.getInfoActivation()))
                .build()).toList();

        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .imageUrl(category.getImageUrl())
                .products(productDTOS)
                .infoActivation(mapToDTO(category.getInfoActivation()))
                .build();
    }


    Category mapToDomain(CategoryDTO categoryDTO);
    PageResponseDTO<CategoryDTO> mapToPageResponseDto(PageResponse<Category> pageResponse);
    InfoActivationDTO mapToDTO(InfoActivation infoActivation);
    InfoActivation mapToDomain(InfoActivationDTO infoActivation);
}
