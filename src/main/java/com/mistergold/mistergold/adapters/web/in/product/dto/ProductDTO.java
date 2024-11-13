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
        @NotBlank(message = "O campo 'Nome' deve ser preenchido.")
        String name,
        String imageUrl,
        @NotBlank(message = "O campo 'Descrição' deve ser preenchido.")
        String description,
        @NotNull(message = "O campo 'Tamanho' não deve ser nulo.")
        @Positive(message = "O campo 'Tamanho' não deve receber valores negativos ou igual a zero.")
        Double size,
        @NotBlank(message = "O campo 'Cor' deve ser preenchido.")
        String color,
        @NotNull(message = "O campo 'Peso' não deve ser nulo.")
        @Positive(message = "O campo 'Peso' não deve receber valores negativos ou igual a zero.")
        Double weight,
        @NotBlank(message = "O campo 'Material' deve ser preenchido.")
        String material,
        @NotNull(message = "O campo 'Preço' não deve ser nulo.")
        @PositiveOrZero(message = "O campo 'Preço' não deve receber valores negativos.")
        Double price,
        @NotNull(message = "O campo 'Quantidade' não deve ser nulo.")
        @PositiveOrZero(message = "O campo 'Quantidade' não deve receber valores negativos.")
        Integer quantity,
        @Size(min = 1, message = "O campo 'Categorias' deve ter ao menos uma categoria associada.")
        Set<CategoryDTO> categories,
        InfoActivationDTO infoActivation
) {
}
