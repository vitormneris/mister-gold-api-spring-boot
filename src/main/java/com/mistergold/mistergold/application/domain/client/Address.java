package com.mistergold.mistergold.application.domain.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String postalCode;
    private Integer number;
    private String complement;
}
