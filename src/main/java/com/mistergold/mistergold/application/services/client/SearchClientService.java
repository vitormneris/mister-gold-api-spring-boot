package com.mistergold.mistergold.application.services.client;

import com.mistergold.mistergold.application.domain.client.Client;
import com.mistergold.mistergold.application.ports.in.client.SearchClientUseCase;
import com.mistergold.mistergold.application.ports.out.client.SearchClientPort;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchClientService implements SearchClientUseCase {
    private final SearchClientPort SearchClientPort;

    @Override
    public Client findById(String id) {
        return SearchClientPort.findById(id);
    }

    @Override
    public Client findByEmail(String email) {
        return SearchClientPort.findByEmail(email);
    }

    @Override
    public List<Client> findAll() {
        return SearchClientPort.findAll();
    }
}
