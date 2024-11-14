package com.mistergold.mistergold.adapters.web.in.message.dto;

import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record MessageDTO(
        String id,
        @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$", message = "nome")
        @NotBlank(message = "nome")
        String senderName,
        @Pattern(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", message = "e-mail")
        @NotBlank(message = "e-mail")
        String senderEmail,
        @NotBlank(message = "texto")
        String text,
        InfoActivationDTO infoActivation
) {

}
