package com.mistergold.mistergold.adapters.web.in.category.dto;

import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import com.mistergold.mistergold.adapters.web.in.product.dto.ProductDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;

@Builder
public record CategoryDTO(
        String id,
        @NotBlank(message = "O campo 'Nome' deve ser preenchido.")
        String name,
        @NotBlank(message = "O campo 'Imagem' deve ser preenchido.")
        String imageUrl,
        @NotBlank(message = "O campo 'Descrição' deve ser preenchido.")
        String description,
        List<ProductDTO> products,
        InfoActivationDTO infoActivation
) {

}
