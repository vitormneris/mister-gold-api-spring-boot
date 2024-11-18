package com.mistergold.mistergold.adapters.web.in.product.dto;

import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import com.mistergold.mistergold.adapters.web.in.category.dto.CategoryDTO;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public record ProductDTO(
        String id,
        @NotBlank(message = "Nome")
        String name,
        String imageUrl,
        @NotBlank(message = "Descrição")
        String description,
        @NotNull(message = "Preço")
        @PositiveOrZero(message = "Preço")
        Double price,
        @NotNull(message = "Quantidade")
        @PositiveOrZero(message = "Quantidade")
        Integer quantity,
        @Size(min = 1, message = "Categoria")
        Set<CategoryDTO> categories,
        InfoActivationDTO infoActivation
) {
}
