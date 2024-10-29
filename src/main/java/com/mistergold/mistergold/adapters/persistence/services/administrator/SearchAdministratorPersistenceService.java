package com.mistergold.mistergold.adapters.persistence.services.administrator;

import com.mistergold.mistergold.adapters.persistence.mappers.AdministratorPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.AdministratorRepository;
import com.mistergold.mistergold.application.domain.administrator.Administrator;
import com.mistergold.mistergold.application.ports.out.administrator.SearchAdministratorPort;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchAdministratorPersistenceService implements SearchAdministratorPort {
    private final AdministratorRepository administratorRepository;
    private final AdministratorPersistenceMapper mapper;

    @Override
    public Administrator findById(String id) {
        return mapper.mapToDomain(administratorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0007)));
    }

    @Override
    public Administrator findByEmail(String email) {
        return mapper.mapToDomain(administratorRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0007)));
    }

    @Override
    public Boolean checkEmailExists(String email) {
        return administratorRepository.findByEmail(email).isPresent();
    }

    @Override
    public List<Administrator> findAll() {
        return mapper.mapListToDomain(administratorRepository.findAll());
    }
}
