package com.mistergold.mistergold.adapters.persistence.entities.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Builder
public class AddressEntity {
    @Field(name = "estado")
    private String state;
    @Field(name = "cidade")
    private String city;
    @Field(name = "bairro")
    private String neighborhood;
    @Field(name = "rua")
    private String street;
    @Field(name = "CEP")
    private String postalCode;
    @Field(name = "numero_residencia")
    private Integer number;
    @Field(name = "complement")
    private String complement;
}
