package com.mistergold.mistergold.adapters.web.in.product.dto;

import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import jakarta.validation.constraints.*;

public record ProductDTO(
        String id,
        @NotBlank(message = "O campo 'Nome' deve ser preenchido.")
        String name,
        @NotBlank(message = "O campo 'Imagem' deve ser preenchido.")
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
        InfoActivationDTO infoActivation
) {
}
