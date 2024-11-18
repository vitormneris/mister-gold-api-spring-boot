package com.mistergold.mistergold.adapters.persistence.services.administrator;

import com.mistergold.mistergold.adapters.persistence.entities.administrator.AdministratorEntity;
import com.mistergold.mistergold.adapters.persistence.mappers.AdministratorPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.AdministratorRepository;
import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.administrator.Administrator;
import com.mistergold.mistergold.application.ports.out.administrator.SearchAdministratorPort;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchAdministratorPersistenceService implements SearchAdministratorPort {
    private final AdministratorRepository administratorRepository;
    private final AdministratorPersistenceMapper administratorMapper;

    @Override
    public Administrator findById(String id) {
        return administratorMapper.mapToDomain(administratorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0007)));
    }

    @Override
    public Administrator findByEmail(String email) {
        return administratorMapper.mapToDomain(administratorRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0007)));
    }

    @Override
    public Boolean checkEmailExists(String email) {
        return administratorRepository.findByEmail(email).isPresent();
    }

    @Override
    public Set<Administrator> findAll() {
        return administratorMapper.mapListToDomain(new HashSet<>(administratorRepository.findAll()));
    }

    @Override
    public PageResponse<Administrator> findByPagination(Boolean isActive, Integer page, Integer pageSize, String name) {
        return administratorMapper.mapToPageResponseDomain(
                administratorRepository.findByPagination(isActive, PageRequest.of(page, pageSize), (name == null) ? "" : name)
        );
    }
}
