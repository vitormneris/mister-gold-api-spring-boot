package com.mistergold.mistergold.configuration.web.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RunErrorEnum {
    ERR0001("USER_NOT_FOUND",  "O usuário não foi encontrado."),
    ERR0002("DUPLICATED_EMAIL",  "O e-mail informado já existe na base de dados."),
    ERR0003("INVALID_ARGUMENTS",  "Os campos a seguir estão inválidos."),
    ERR0004("REDUNDANT_DEACTIVATION",  "Esta conta já está desativada.");

    private final String code;
    private final String message;
}
