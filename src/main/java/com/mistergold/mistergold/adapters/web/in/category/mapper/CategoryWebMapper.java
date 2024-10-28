package com.mistergold.mistergold.adapters.web.in.category.mapper;

import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import com.mistergold.mistergold.adapters.web.in.category.dto.CategoryDTO;
import com.mistergold.mistergold.adapters.web.in.product.dto.ProductDTO;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.category.Category;
import com.mistergold.mistergold.application.domain.product.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryWebMapper {
    List<CategoryDTO> mapToDTO(List<Category> categories);
    CategoryDTO mapToDTO(Category category);
    Category mapToDomain(CategoryDTO categoryDTO);
    InfoActivationDTO mapToDTO(InfoActivation infoActivation);
    InfoActivation mapToDomain(InfoActivationDTO infoActivation);
}
