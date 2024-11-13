package com.mistergold.mistergold.adapters.web.in.client.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AddressDTO(
        @NotBlank(message = "estado")
        String state,
        @NotBlank(message = "cidade")
        String city,
        @NotBlank(message = "bairro")
        String neighborhood,
        @NotBlank(message = "rua")
        String street,
        @NotBlank(message = "CEP")
        String postalCode,
        @NotNull(message = "número")
        Integer number,
        @NotBlank(message = "complemento")
        String complement
) {
}
