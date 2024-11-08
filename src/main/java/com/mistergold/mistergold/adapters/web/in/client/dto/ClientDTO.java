package com.mistergold.mistergold.adapters.web.in.client.dto;

import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;

import com.mistergold.mistergold.configuration.web.enums.UserRoleEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ClientDTO(
        String id,
        @NotBlank(message = "O campo 'Nome' deve ser preenchido.")
        String name,
        @Email(message = "O e-mail fornecido deve ser válido.")
        @NotBlank(message = "O campo 'E-mail' deve ser preenchido.")
        String email,
        @NotBlank(message = "O campo 'Telefone' deve ser preenchido.")
        String phone,
        UserRoleEnum role,
        @Valid
        @NotNull(message = "O campo 'Endereço' não deve ser nulo.")
        AddressDTO address,
        @NotBlank(message = "O campo 'Senha' deve ser preenchido.")
        String password,
        InfoActivationDTO infoActivation
) {
}
