package com.mistergold.mistergold.adapters.email.dto;

import lombok.Builder;

@Builder
public record EmailDTO(
        String from,
        String to,
        String subject,
        String text
) {
}
