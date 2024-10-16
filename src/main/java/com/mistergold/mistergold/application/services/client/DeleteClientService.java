package com.mistergold.mistergold.application.services.client;

import com.mistergold.mistergold.application.domain.client.Client;
import com.mistergold.mistergold.application.ports.in.client.DeleteClientUseCase;
import com.mistergold.mistergold.application.ports.out.client.DeleteClientPort;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DeleteClientService implements DeleteClientUseCase {
    private final DeleteClientPort deleteClientPort;

    @Override
    public void delete(String id) {
        deleteClientPort.delete(id);
    }

    @Override
    public Client inactivate(String id) {
        return deleteClientPort.inactivate(id);
    }
}
