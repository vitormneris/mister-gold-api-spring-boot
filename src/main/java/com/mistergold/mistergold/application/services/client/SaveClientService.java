package com.mistergold.mistergold.application.services.client;

import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.client.Client;
import com.mistergold.mistergold.application.ports.in.client.SaveClientUseCase;
import com.mistergold.mistergold.application.ports.out.client.SaveClientPort;
import com.mistergold.mistergold.application.ports.out.client.SearchClientPort;
import com.mistergold.mistergold.configuration.web.advice.exception.DataIntegratyViolationException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;

import lombok.RequiredArgsConstructor;

import java.time.Instant;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveClientService implements SaveClientUseCase {
    private final SearchClientPort searchClientPort;
    private final SaveClientPort saveClientPort;

    @Override
    public Client save(Client client) {
        client.setId(null);

        if (searchClientPort.checkEmailExists(client.getEmail())) throw new DataIntegratyViolationException(RunErrorEnum.ERR0002);

        InfoActivation infoActivation = InfoActivation.builder()
            .creationDate(Instant.now())
            .isActive(true)
            .build();

        client.setInfoActivation(infoActivation);

        return saveClientPort.save(client);
    }
}
