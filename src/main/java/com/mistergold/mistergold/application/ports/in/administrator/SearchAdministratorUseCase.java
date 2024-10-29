package com.mistergold.mistergold.application.ports.in.administrator;

import com.mistergold.mistergold.application.domain.administrator.Administrator;

import java.util.List;

public interface SearchAdministratorUseCase {
    List<Administrator> findAll();
    Administrator findById(String id);
    Administrator findByEmail(String email);
}
