package com.mistergold.mistergold.application.ports.in.administrator;

import com.mistergold.mistergold.application.domain.administrator.Administrator;

public interface DeleteAdministratorUseCase {
    void delete(String id);
    Administrator inactivate(String id);
}
