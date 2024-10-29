package com.mistergold.mistergold.application.ports.in.administrator;

import com.mistergold.mistergold.application.domain.administrator.Administrator;

public interface UpdateAdministratorUseCase {
    Administrator update(Administrator administrator, String id);
}
