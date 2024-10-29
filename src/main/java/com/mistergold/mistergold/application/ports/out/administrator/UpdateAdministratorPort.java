package com.mistergold.mistergold.application.ports.out.administrator;

import com.mistergold.mistergold.application.domain.administrator.Administrator;

public interface UpdateAdministratorPort {
    Administrator update(Administrator administrator, String id);
}
