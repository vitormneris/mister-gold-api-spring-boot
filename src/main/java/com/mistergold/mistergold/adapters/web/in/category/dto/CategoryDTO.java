package com.mistergold.mistergold.adapters.web.in.category.dto;

import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import com.mistergold.mistergold.adapters.web.in.product.dto.ProductDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.Set;

@Builder
public record CategoryDTO(
        String id,
        @NotBlank(message = "Nome")
        String name,
        String imageUrl,
        @NotBlank(message = "Descrição")
        String description,
        Set<ProductDTO> products,
        InfoActivationDTO infoActivation
) {

}
