package com.mistergold.mistergold.application.ports.out.administrator;

import com.mistergold.mistergold.application.domain.administrator.Administrator;

public interface DeleteAdministratorPort {
    void delete(String id);
    Administrator inactivate(String id);
}
