package com.mistergold.mistergold.adapters.persistence.services;

import com.mistergold.mistergold.adapters.persistence.mappers.UserPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.UserRepository;
import com.mistergold.mistergold.application.domain.user.User;
import com.mistergold.mistergold.application.ports.out.user.SearchUserPort;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchUserPersistenceService implements SearchUserPort {
    private final UserRepository userRepository;
    private final UserPersistenceMapper mapper;

    @Override
    public User findById(String id) {
        return mapper.mapToDomain(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0001)));
    }

    @Override
    public User findByEmail(String email) {
        return mapper.mapToDomain(userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0001)));
    }

    @Override
    public Boolean checkEmailExists(String email) {
        if (userRepository.findByEmail(email).isPresent()) return true;
        return false; 
    }

    @Override
    public List<User> findAll() {
        return mapper.mapListToDomain(userRepository.findAll());
    }
}
