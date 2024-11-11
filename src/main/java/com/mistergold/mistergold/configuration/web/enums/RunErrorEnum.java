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
    ERR0007("ADMINISTRATOR_NOT_FOUND",  "O administrador não foi encontrado."),
    ERR0008("PRODUCT_WITHOUT_CATEGORY",  "Não é possível deletar esta categoria porque há produtos que a têm como única categoria associada."),
    ERR0009("ORDER_NOT_FOUND",  "O pedido não foi encontrada."),
    ERR0010("ORDER_STATUS_INVALID",  "O status de pedido informado é inválido."),
    ERR0011("USER_NOT_FOUND",  "O e-mail informado não existe na base de dados."),
    ERR0012("UPLOAD_IMAGE_ERROR",  "Houve um error ao fazer o upload da imagem.");


    private final String code;
    private final String message;
}
