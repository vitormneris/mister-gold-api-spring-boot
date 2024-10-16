package com.mistergold.mistergold.application.ports.out.client;

import com.mistergold.mistergold.application.domain.client.Client;

public interface DeleteClientPort {
    void delete(String id);
    Client inactivate(String id);
}
