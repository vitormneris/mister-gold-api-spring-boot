package com.mistergold.mistergold.adapters.web.in;

import java.time.Instant;

public record InfoActivationDTO(
        Boolean isActive,
        Instant creationDate,
        Instant deactivationDate
) {
}
