package com.mistergold.mistergold.adapters.web.in.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import com.mistergold.mistergold.adapters.web.in.client.dto.ClientDTO;
import com.mistergold.mistergold.configuration.web.enums.OrderStatusEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.Instant;
import java.util.Set;

@Builder
public record OrderDTO(
        String id,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "-03:00")
        Instant moment,
        OrderStatusEnum orderStatus,
        @NotNull(message = "O campo 'Cliente' não deve ser nulo.")
        ClientDTO client,
        Double totalPrice,
        @Valid
        @Size(min = 1, message = "O campo 'itens' deve ter ao menos um item.")
        Set<OrderItemDTO> items,
        InfoActivationDTO infoActivation
) {
}
