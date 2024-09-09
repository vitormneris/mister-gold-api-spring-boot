package com.mistergold.mistergold.adapters.persistence.services;

import com.mistergold.mistergold.adapters.persistence.mappers.UserPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.UserRepository;
import com.mistergold.mistergold.application.domain.user.User;
import com.mistergold.mistergold.application.ports.out.user.SaveUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveUserPersistenceService implements SaveUserPort {
    private final UserRepository repository;
    private final UserPersistenceMapper mapper;

    @Override
    public User save(User user) {
        return mapper.mapToDomain(repository.save(mapper.mapToEntity(user)));
    }
}
