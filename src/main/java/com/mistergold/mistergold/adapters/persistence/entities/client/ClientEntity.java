package com.mistergold.mistergold.adapters.persistence.entities.client;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.mistergold.mistergold.adapters.persistence.entities.abstracts.UserEntityAbstract;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "clientes")
public class ClientEntity extends UserEntityAbstract {
    @Field(name = "telefone")
    private String phone;
    @Field(name = "endereco")
    private AddressEntity address;
    @Field(name = "pedido")
    private String orderId;
}
