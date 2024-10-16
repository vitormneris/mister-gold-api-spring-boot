package com.mistergold.mistergold.application.ports.out.client;

import java.util.List;

import com.mistergold.mistergold.application.domain.client.Client;

public interface SearchClientPort {
    List<Client> findAll();
    Client findById(String id);
    Client findByEmail(String email);
    Boolean checkEmailExists(String email);
}
