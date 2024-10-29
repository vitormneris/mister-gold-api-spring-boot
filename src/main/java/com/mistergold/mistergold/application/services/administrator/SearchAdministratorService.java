package com.mistergold.mistergold.application.services.administrator;

import com.mistergold.mistergold.application.domain.administrator.Administrator;
import com.mistergold.mistergold.application.ports.in.administrator.SearchAdministratorUseCase;
import com.mistergold.mistergold.application.ports.out.administrator.SearchAdministratorPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchAdministratorService implements SearchAdministratorUseCase {
    private final SearchAdministratorPort searchAdministratorPort;

    @Override
    public Administrator findById(String id) {
        return searchAdministratorPort.findById(id);
    }

    @Override
    public Administrator findByEmail(String email) {
        return searchAdministratorPort.findByEmail(email);
    }

    @Override
    public List<Administrator> findAll() {
        return searchAdministratorPort.findAll();
    }
}
