package com.mistergold.mistergold.application.ports.in.client;

import com.mistergold.mistergold.application.domain.client.Client;

public interface UpdateClientUseCase {
    Client update(Client client, String id);
}
