package com.mistergold.mistergold.adapters.web.in.client.dto;

import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;

import com.mistergold.mistergold.adapters.web.in.order.dto.OrderDTO;
import com.mistergold.mistergold.configuration.web.enums.UserRoleEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Set;

@Builder
public record ClientDTO(
        String id,
        @NotBlank(message = "nome")
        String name,
        @Email(message = "e-mail")
        @NotBlank(message = "e-mail")
        String email,
        @NotBlank(message = "telefone")
        String phone,
        UserRoleEnum role,
        @Valid
        @NotNull(message = "endereço")
        AddressDTO address,
        Set<OrderDTO> order,
        @NotBlank(message = "senha")
        String password,
        InfoActivationDTO infoActivation
) {

}
