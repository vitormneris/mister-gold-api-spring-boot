package com.mistergold.mistergold.application.ports.in.client;

import java.util.List;

import com.mistergold.mistergold.application.domain.client.Client;

public interface SearchClientUseCase {
    List<Client> findAll();
    Client findById(String id);
    Client findByEmail(String email);
}
