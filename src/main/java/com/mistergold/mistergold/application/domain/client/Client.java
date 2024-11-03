package com.mistergold.mistergold.application.domain.client;

import com.mistergold.mistergold.application.domain.abstracts.UserAbstract;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client extends UserAbstract {
    private String phone;
    private Address address;

    public Client(String id) {
        this.id = id;
    }
}
