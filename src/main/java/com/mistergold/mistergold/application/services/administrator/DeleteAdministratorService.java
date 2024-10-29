package com.mistergold.mistergold.application.services.administrator;

import com.mistergold.mistergold.application.domain.administrator.Administrator;
import com.mistergold.mistergold.application.ports.in.administrator.DeleteAdministratorUseCase;
import com.mistergold.mistergold.application.ports.out.administrator.DeleteAdministratorPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteAdministratorService implements DeleteAdministratorUseCase {
    private final DeleteAdministratorPort deleteAdministratorPort;

    @Override
    public void delete(String id) {
        deleteAdministratorPort.delete(id);
    }

    @Override
    public Administrator inactivate(String id) {
        return deleteAdministratorPort.inactivate(id);
    }
}
