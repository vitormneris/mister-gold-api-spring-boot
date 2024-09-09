package com.mistergold.mistergold.adapters.web.in.user.dto;

import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;

public record UserDTO(
        String id,
        String name,
        String email,
        String password,
        InfoActivationDTO infoActivation
) {
}
