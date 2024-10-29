package com.mistergold.mistergold.adapters.persistence.services.administrator;

import com.mistergold.mistergold.adapters.persistence.mappers.AdministratorPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.AdministratorRepository;
import com.mistergold.mistergold.application.domain.administrator.Administrator;
import com.mistergold.mistergold.application.ports.out.administrator.SaveAdministratorPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveAdministratorPersistenceService implements SaveAdministratorPort {
    private final AdministratorRepository administratorRepository;
    private final AdministratorPersistenceMapper mapper;

    @Override
    public Administrator save(Administrator administrator) {
        return mapper.mapToDomain(administratorRepository.save(mapper.mapToEntity(administrator)));
    }
}
