package com.mistergold.mistergold.adapters.web.in.administrator.dto;

import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import com.mistergold.mistergold.configuration.web.enums.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AdministratorDTO(
    String id,
    @NotBlank(message = "nome")
    String name,
    @Email(message = "e-mail")
    @NotBlank(message = "e-mail")
    String email,
    UserRoleEnum role,
    @NotBlank(message = "senha")
    String password,
    InfoActivationDTO infoActivation
) {
}
