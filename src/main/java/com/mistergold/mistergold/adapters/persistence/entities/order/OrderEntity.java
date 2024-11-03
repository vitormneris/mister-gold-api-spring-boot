package com.mistergold.mistergold.adapters.persistence.entities.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mistergold.mistergold.adapters.persistence.entities.InfoActivationEntity;
import lombok.*;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pedidos")
public class OrderEntity {
    @Id
    private String id;
    @Field(name = "momento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "-03:00")
    private Instant moment;
    @Field(name = "status_pedido")
    private Integer orderStatus;
    @Field(name = "client_id")
    private String clientId;
    @Field(name = "preco_total")
    private Double totalPrice;
    @Field(name = "items")
    private Set<OrderItemEntity> items =  new HashSet<>();
    @Field(name = "status_ativacao")
    private InfoActivationEntity infoActivation;
}
