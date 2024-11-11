package com.mistergold.mistergold.application.ports.in.administrator;

import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.administrator.Administrator;

public interface SearchAdministratorUseCase {
    PageResponse<Administrator> findByPagination(Boolean isActive, Integer page, Integer pageSize, String name);
    Administrator findById(String id);
    Administrator findByEmail(String email);
}
