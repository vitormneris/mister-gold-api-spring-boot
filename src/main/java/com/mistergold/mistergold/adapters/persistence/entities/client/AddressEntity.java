package com.mistergold.mistergold.adapters.persistence.entities.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressEntity {
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String postalCode;
    private Integer number;
}
