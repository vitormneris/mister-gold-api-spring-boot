package com.mistergold.mistergold.application.services.client;

import com.mistergold.mistergold.application.domain.abstracts.Recovery;
import com.mistergold.mistergold.application.domain.client.Client;
import com.mistergold.mistergold.application.ports.in.client.UpdateClientUseCase;
import com.mistergold.mistergold.application.ports.out.client.SearchClientPort;
import com.mistergold.mistergold.application.ports.out.client.UpdateClientPort;

import com.mistergold.mistergold.configuration.web.advice.exception.NotAuthorizationException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateClientService implements UpdateClientUseCase {
    private final SearchClientPort searchClientPort;
    private final UpdateClientPort updateClientPort;

    @Override
    public Client update(Client clientNew, String id) {
        clientNew.setId(id);
        return updateClientPort.update(clientNew, id);
    }

    @Override
    public void recoveryPasswordSet(String email, Recovery recovery) {
        Client client = searchClientPort.findByEmail(email);

        if (client.getCode() == null) throw new NotAuthorizationException(RunErrorEnum.ERR0014);

        if (!client.getCode().equals(recovery.getCode())) {
            updateClientPort.updatePassword(client, client.getId());
            throw new NotAuthorizationException(RunErrorEnum.ERR0013);
        }

        client.setPassword(new BCryptPasswordEncoder().encode(recovery.getPassword()));
        updateClientPort.updatePassword(client, client.getId());
    }
}
