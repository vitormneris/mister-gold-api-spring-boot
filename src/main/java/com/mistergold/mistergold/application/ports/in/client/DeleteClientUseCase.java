package com.mistergold.mistergold.application.ports.in.client;

import com.mistergold.mistergold.application.domain.client.Client;

public interface DeleteClientUseCase {
    void delete(String id);
    Client inactivate(String id);
}
