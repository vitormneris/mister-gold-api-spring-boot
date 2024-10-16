package com.mistergold.mistergold.adapters.web.in.client.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressDTO(
        @NotBlank(message = "O campo 'Estado' deve ser preenchido.") 
        String state,
        @NotBlank(message = "O campo 'Cidade' deve ser preenchido.") 
        String city,
        @NotBlank(message = "O campo 'Bairro' deve ser preenchido.") 
        String neighborhood,
        @NotBlank(message = "O campo 'Rua' deve ser preenchido.") 
        String street,
        @NotBlank(message = "O campo 'CEP' deve ser preenchido.") 
        String postalCode,
        @NotBlank(message = "O campo 'Número' deve ser preenchido.") 
        Integer number
) {
}
