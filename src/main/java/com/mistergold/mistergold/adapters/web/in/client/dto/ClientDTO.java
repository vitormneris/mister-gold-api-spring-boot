package com.mistergold.mistergold.adapters.web.in.client.dto;

import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;

import com.mistergold.mistergold.adapters.web.in.order.dto.OrderDTO;
import com.mistergold.mistergold.configuration.web.enums.UserRoleEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.util.Set;

@Builder
public record ClientDTO(
        String id,
        @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$", message = "nome")
        @NotBlank(message = "nome")
        String name,
        @Pattern(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", message = "e-mail")
        @NotBlank(message = "e-mail")
        String email,
        @Pattern(regexp = "^(\\+55)?\\s?(\\(?\\d{2}\\)?\\s?)?9?\\d{4}-?\\d{4}$", message = "telefone")
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
