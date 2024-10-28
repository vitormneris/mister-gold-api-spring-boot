package com.mistergold.mistergold.adapters.web.in.category.dto;

import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;

public record CategoryDTO(
        String id,
        String name,
        String imageUrl,
        String description,
        InfoActivationDTO infoActivation
) {

}
