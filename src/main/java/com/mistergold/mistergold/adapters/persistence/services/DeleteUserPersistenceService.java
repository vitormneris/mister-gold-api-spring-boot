package com.mistergold.mistergold.adapters.persistence.services;

import com.mistergold.mistergold.adapters.persistence.entities.user.UserEntity;
import com.mistergold.mistergold.adapters.persistence.mappers.UserPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.UserRepository;
import com.mistergold.mistergold.application.domain.user.User;
import com.mistergold.mistergold.application.ports.out.user.DeleteUserPort;
import com.mistergold.mistergold.configuration.web.advice.exception.DataIntegratyViolationException;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class DeleteUserPersistenceService implements DeleteUserPort {
    private final UserRepository userRepository;
    private final UserPersistenceMapper mapper;

    @Override
    public void delete(String id) {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0001));
        userRepository.deleteById(id);
    }

    @Override
    public User inactivate(String id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0001));
        if (user.getInfoActivation().getIsActive()) {
            user.getInfoActivation().setIsActive(false);
            user.getInfoActivation().setDeactivationDate(Instant.now());
        } else throw new DataIntegratyViolationException(RunErrorEnum.ERR0004);
        return mapper.mapToDomain(userRepository.save(user));
    }
}
