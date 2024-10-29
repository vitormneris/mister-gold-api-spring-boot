package com.mistergold.mistergold.application.services.administrator;

import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.administrator.Administrator;
import com.mistergold.mistergold.application.domain.client.Client;
import com.mistergold.mistergold.application.ports.in.administrator.SaveAdministratorUseCase;
import com.mistergold.mistergold.application.ports.in.client.SaveClientUseCase;
import com.mistergold.mistergold.application.ports.out.administrator.SaveAdministratorPort;
import com.mistergold.mistergold.application.ports.out.administrator.SearchAdministratorPort;
import com.mistergold.mistergold.application.ports.out.client.SaveClientPort;
import com.mistergold.mistergold.application.ports.out.client.SearchClientPort;
import com.mistergold.mistergold.configuration.web.advice.exception.DataIntegratyViolationException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class SaveAdministratorService implements SaveAdministratorUseCase {
    private final SearchAdministratorPort searchAdministratorPort;
    private final SaveAdministratorPort saveAdministratorPort;

    @Override
    public Administrator save(Administrator administrator) {
        if (searchAdministratorPort.checkEmailExists(administrator.getEmail())) throw new DataIntegratyViolationException(RunErrorEnum.ERR0002);

        InfoActivation infoActivation = InfoActivation.builder()
            .creationDate(Instant.now())
            .isActive(true)
            .build();

        administrator.setInfoActivation(infoActivation);

        return saveAdministratorPort.save(administrator);
    }
}
