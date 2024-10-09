package com.mistergold.mistergold.configuration.web.advice.dto;

import lombok.Builder;

@Builder
public record ErrorFieldDTO(
    String name,
    String description, 
    String value
) {

}