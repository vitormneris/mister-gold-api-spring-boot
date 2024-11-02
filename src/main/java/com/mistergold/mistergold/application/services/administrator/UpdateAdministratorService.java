package com.mistergold.mistergold.application.services.administrator;

import com.mistergold.mistergold.application.domain.administrator.Administrator;
import com.mistergold.mistergold.application.ports.in.administrator.UpdateAdministratorUseCase;
import com.mistergold.mistergold.application.ports.out.administrator.UpdateAdministratorPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateAdministratorService implements UpdateAdministratorUseCase {
    private final UpdateAdministratorPort updateAdministratorPort;

    @Override
    public Administrator update(Administrator administratorNew, String id) {
        administratorNew.setId(id);

        return updateAdministratorPort.update(administratorNew, id);
    }
}
