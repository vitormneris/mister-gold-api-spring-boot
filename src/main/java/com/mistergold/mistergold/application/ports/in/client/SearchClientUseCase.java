package com.mistergold.mistergold.application.ports.in.client;

import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.client.Client;

public interface SearchClientUseCase {
    PageResponse<Client> findByPagination(Boolean isActive, Integer page, Integer pageSize, String name);
    Client findById(String id);
    void recoveryPasswordGenerator(String email);
}
