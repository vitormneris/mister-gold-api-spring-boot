package com.mistergold.mistergold.adapters.persistence.services.administrator;

import com.mistergold.mistergold.adapters.persistence.entities.administrator.AdministratorEntity;
import com.mistergold.mistergold.adapters.persistence.mappers.AdministratorPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.AdministratorRepository;
import com.mistergold.mistergold.application.domain.administrator.Administrator;
import com.mistergold.mistergold.application.ports.out.administrator.UpdateAdministratorPort;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateAdministratorPersistenceService implements UpdateAdministratorPort {
    private final AdministratorRepository administratorRepository;
    private final AdministratorPersistenceMapper mapper;

    @Override
    public Administrator update(Administrator administratorNew, String id) {
        AdministratorEntity administratorOld = administratorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0007));
        administratorOld.setName(administratorNew.getName() == null ? administratorOld.getName() : administratorNew.getName());
        administratorOld.setEmail(administratorNew.getEmail() == null ? administratorOld.getEmail() : administratorNew.getEmail());

        return mapper.mapToDomain(administratorRepository.save(administratorOld));
    }
}
