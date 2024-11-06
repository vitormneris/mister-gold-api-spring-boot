package com.mistergold.mistergold.configuration.web.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRoleEnum {
    ADMINISTRATOR("administrator"),
    CLIENT("client");

    private final String role;
}
