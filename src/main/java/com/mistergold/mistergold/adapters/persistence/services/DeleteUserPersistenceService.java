package com.mistergold.mistergold.adapters.persistence.services;

import com.mistergold.mistergold.adapters.persistence.repositories.UserRepository;
import com.mistergold.mistergold.application.ports.out.user.DeleteUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserPersistenceService implements DeleteUserPort {
    private final UserRepository repository;

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
