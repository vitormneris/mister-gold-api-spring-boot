package com.mistergold.mistergold.configuration.web.advice.dto;

import lombok.Builder;
import java.util.List;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

@Builder
public record ErrorMessageResponseDTO(
    String code,
    String message,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    Instant timestamp,
    String path,
    List<ErrorFieldDTO> fields
) {

}
