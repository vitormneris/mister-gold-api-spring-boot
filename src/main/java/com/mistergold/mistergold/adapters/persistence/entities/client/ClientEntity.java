package com.mistergold.mistergold.adapters.persistence.entities.client;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.mistergold.mistergold.adapters.persistence.entities.abstracts.UserEntityAbstract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "clientes")
public class ClientEntity extends UserEntityAbstract {
    @Field(name = "telefone")
    private String phone;
    @Field(name = "endereco")
    private AddressEntity address;
}
