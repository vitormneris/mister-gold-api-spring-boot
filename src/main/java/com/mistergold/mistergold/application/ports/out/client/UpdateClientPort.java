package com.mistergold.mistergold.application.ports.out.client;

import com.mistergold.mistergold.application.domain.client.Client;

public interface UpdateClientPort {
    Client update(Client client, String id);
    Client updatePassword(Client client, String id);
}
