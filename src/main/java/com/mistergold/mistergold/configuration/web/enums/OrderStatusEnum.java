package com.mistergold.mistergold.configuration.web.enums;

import com.mistergold.mistergold.configuration.web.advice.exception.ArgumentInvalidException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatusEnum {
    WAITING_PAYMENT(1, "Esperando pagamento."),
    PAID(2, "Pago."),
    SHIPPED(3, "Enviado."),
    DELIVERED(4, "Entregue."),
    CANCELED(5, "Cancelado."),;

    private final Integer code;
    private final String message;

    public static OrderStatusEnum valueOf(Integer code) {
        for (OrderStatusEnum status : OrderStatusEnum.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new ArgumentInvalidException(RunErrorEnum.ERR0004);
    }
}
