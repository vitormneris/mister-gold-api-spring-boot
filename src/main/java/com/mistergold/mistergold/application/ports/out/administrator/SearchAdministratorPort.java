package com.mistergold.mistergold.application.ports.out.administrator;

import com.mistergold.mistergold.application.domain.administrator.Administrator;

import java.util.List;

public interface SearchAdministratorPort {
    List<Administrator> findAll();
    Administrator findById(String id);
    Administrator findByEmail(String email);
    Boolean checkEmailExists(String email);
}
