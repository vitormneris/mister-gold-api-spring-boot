package com.mistergold.mistergold.application.ports.out.client;

import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.client.Client;

public interface SearchClientPort {
    PageResponse<Client> findByPagination(Boolean isActive, Integer page, Integer pageSize, String name);
    Client findById(String id);
    Client findByEmail(String email);
    Boolean checkEmailExists(String email);
}
