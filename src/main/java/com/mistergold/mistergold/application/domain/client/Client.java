package com.mistergold.mistergold.application.domain.client;

import com.mistergold.mistergold.application.domain.abstracts.UserAbstract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Client extends UserAbstract {
    private String phone;
    private Address address;
}
