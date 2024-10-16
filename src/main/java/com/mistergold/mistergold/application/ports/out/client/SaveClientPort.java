package com.mistergold.mistergold.application.ports.out.client;

import com.mistergold.mistergold.application.domain.client.Client;

public interface SaveClientPort {
    Client save(Client client);
}
