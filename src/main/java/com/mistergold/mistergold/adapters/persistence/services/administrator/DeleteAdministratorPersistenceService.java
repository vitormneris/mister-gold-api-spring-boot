package com.mistergold.mistergold.adapters.persistence.services.administrator;

import com.mistergold.mistergold.adapters.persistence.entities.administrator.AdministratorEntity;
import com.mistergold.mistergold.adapters.persistence.mappers.AdministratorPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.AdministratorRepository;
import com.mistergold.mistergold.application.domain.administrator.Administrator;
import com.mistergold.mistergold.application.ports.out.administrator.DeleteAdministratorPort;
import com.mistergold.mistergold.configuration.web.advice.exception.DataIntegratyViolationException;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class DeleteAdministratorPersistenceService implements DeleteAdministratorPort {
    private final AdministratorRepository administratorRepository;
    private final AdministratorPersistenceMapper mapper;

    @Override
    public void delete(String id) {
        administratorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0007));
        administratorRepository.deleteById(id);
    }

    @Override
    public Administrator inactivate(String id) {
        AdministratorEntity administrator = administratorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0007));
        if (administrator.getInfoActivation().getIsActive()) {
            administrator.getInfoActivation().setIsActive(false);
            administrator.getInfoActivation().setDeactivationDate(Instant.now());
        } else throw new DataIntegratyViolationException(RunErrorEnum.ERR0004);
        return mapper.mapToDomain(administratorRepository.save(administrator));
    }
}
