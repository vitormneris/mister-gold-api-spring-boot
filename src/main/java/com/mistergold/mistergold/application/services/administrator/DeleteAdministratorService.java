package com.mistergold.mistergold.application.services.administrator;

import com.mistergold.mistergold.application.domain.administrator.Administrator;
import com.mistergold.mistergold.application.ports.in.administrator.DeleteAdministratorUseCase;
import com.mistergold.mistergold.application.ports.out.administrator.DeleteAdministratorPort;
import com.mistergold.mistergold.application.ports.out.administrator.SearchAdministratorPort;
import com.mistergold.mistergold.configuration.web.advice.exception.DataIntegratyViolationException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteAdministratorService implements DeleteAdministratorUseCase {
    private final SearchAdministratorPort searchAdministratorPort;
    private final DeleteAdministratorPort deleteAdministratorPort;

    @Override
    public void delete(String id) {
        if (searchAdministratorPort.findAll().size() < 2) throw new DataIntegratyViolationException(RunErrorEnum.ERR0016);
        deleteAdministratorPort.delete(id);
    }

    @Override
    public Administrator inactivate(String id) {
        return deleteAdministratorPort.inactivate(id);
    }
}
