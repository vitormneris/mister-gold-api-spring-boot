package com.mistergold.mistergold.adapters.persistence.services;

import com.mistergold.mistergold.adapters.persistence.mappers.UserPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.UserRepository;
import com.mistergold.mistergold.application.domain.user.User;
import com.mistergold.mistergold.application.ports.out.user.SearchUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchUserPersistenceService implements SearchUserPort {
    private final UserRepository repository;
    private final UserPersistenceMapper mapper;

    @Override
    public User findById(String id) {
        return mapper.mapToDomain(repository.findById(id).orElseThrow());
    }

    @Override
    public User findByEmail(String email) {
        return mapper.mapToDomain(repository.findByEmail(email).orElseThrow());
    }

    @Override
    public Boolean checkEmailExists(String email) {
        if (repository.findByEmail(email).isPresent()) return true;
        return false;
    }
}
