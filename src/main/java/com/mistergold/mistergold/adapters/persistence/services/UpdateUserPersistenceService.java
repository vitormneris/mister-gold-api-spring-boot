package com.mistergold.mistergold.adapters.persistence.services;

import com.mistergold.mistergold.adapters.persistence.entities.user.UserEntity;
import com.mistergold.mistergold.adapters.persistence.mappers.UserPersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.UserRepository;
import com.mistergold.mistergold.application.domain.user.User;
import com.mistergold.mistergold.application.ports.out.user.UpdateUserPort;
import com.mistergold.mistergold.configuration.web.advice.exception.ResourceNotFoundException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserPersistenceService implements UpdateUserPort {
    private final UserRepository userRepository;
    private final UserPersistenceMapper mapper;

    @Override
    public User update(User userNew, String id) {
        UserEntity userOld = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RunErrorEnum.ERR0001));
        userOld.setName(userNew.getName() == null ? userOld.getName() : userNew.getName());
        userOld.setEmail(userNew.getEmail() == null ? userOld.getEmail() : userNew.getEmail());
        return mapper.mapToDomain(userRepository.save(userOld));
    }
}
