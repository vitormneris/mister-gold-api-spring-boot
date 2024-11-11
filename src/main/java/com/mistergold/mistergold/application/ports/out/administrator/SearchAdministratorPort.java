package com.mistergold.mistergold.application.ports.out.administrator;

import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.administrator.Administrator;

import java.util.List;

public interface SearchAdministratorPort {
    PageResponse<Administrator> findByPagination(Boolean isActive, Integer page, Integer pageSize, String name);
    Administrator findById(String id);
    Administrator findByEmail(String email);
    Boolean checkEmailExists(String email);
}
