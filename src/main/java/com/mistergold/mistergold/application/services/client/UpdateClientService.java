package com.mistergold.mistergold.application.services.client;

import com.mistergold.mistergold.application.domain.client.Client;
import com.mistergold.mistergold.application.ports.in.client.UpdateClientUseCase;
import com.mistergold.mistergold.application.ports.out.client.UpdateClientPort;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateClientService implements UpdateClientUseCase {
    private final UpdateClientPort updateClientPort;

    @Override
    public Client update(Client clientNew, String id) {
        return updateClientPort.update(clientNew, id);
    }
}
