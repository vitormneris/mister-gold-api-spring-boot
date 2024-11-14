package com.mistergold.mistergold.adapters.web.in.client.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record AddressDTO(
        @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$", message = "estado")
        @NotBlank(message = "estado")
        String state,
        @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$", message = "cidade")
        @NotBlank(message = "cidade")
        String city,
        @NotBlank(message = "bairro")
        String neighborhood,
        @NotBlank(message = "rua")
        String street,
        @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "CEP")
        @NotBlank(message = "CEP")
        String postalCode,
        @NotNull(message = "número")
        Integer number,
        @NotBlank(message = "complemento")
        String complement
) {
}
