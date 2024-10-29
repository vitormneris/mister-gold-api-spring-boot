package com.mistergold.mistergold.application.ports.out.administrator;

import com.mistergold.mistergold.application.domain.administrator.Administrator;

public interface SaveAdministratorPort {
    Administrator save(Administrator administrator);
}
