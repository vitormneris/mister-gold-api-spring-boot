package com.mistergold.mistergold.adapters.web.in.administrator.dto;

import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import com.mistergold.mistergold.configuration.web.enums.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AdministratorDTO(
    String id,
    @NotBlank(message = "O campo 'Nome' deve ser preenchido.")
    String name,
    @Email(message = "O e-mail fornecido deve ser válido.")
    @NotBlank(message = "O campo 'E-mail' deve ser preenchido.")
    String email,
    UserRoleEnum role,
    @NotBlank(message = "O campo 'Senha' deve ser preenchido.")
    String password,
    InfoActivationDTO infoActivation
) {
}
