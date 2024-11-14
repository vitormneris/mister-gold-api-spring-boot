package com.mistergold.mistergold.adapters.web.in.administrator.dto;

import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import com.mistergold.mistergold.configuration.web.enums.UserRoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AdministratorDTO(
    String id,
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$", message = "nome")
    @NotBlank(message = "nome")
    String name,
    @Pattern(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", message = "e-mail")
    @NotBlank(message = "e-mail")
    String email,
    UserRoleEnum role,
    @NotBlank(message = "senha")
    String password,
    InfoActivationDTO infoActivation
) {
}
