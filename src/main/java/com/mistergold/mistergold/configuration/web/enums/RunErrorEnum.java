package com.mistergold.mistergold.configuration.web.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RunErrorEnum {
    ERR0001("CLIENT_NOT_FOUND",  "O cliente não foi encontrado."),
    ERR0002("DUPLICATED_EMAIL",  "O e-mail informado já existe na base de dados."),
    ERR0003("INVALID_ARGUMENTS",  "Os campos a seguir estão inválidos."),
    ERR0004("REDUNDANT_DEACTIVATION",  "Este recurso já está desativado."),
    ERR0005("PRODUCT_NOT_FOUND",  "O produto não foi encontrado."),
    ERR0006("CATEGORY_NOT_FOUND",  "A categoria não foi encontrada."),
    ERR0007("ADMINISTRATOR_NOT_FOUND",  "O administrador não foi encontrado.");

    private final String code;
    private final String message;
}
