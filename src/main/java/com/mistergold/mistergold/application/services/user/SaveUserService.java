package com.mistergold.mistergold.application.services.user;

import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.user.User;
import com.mistergold.mistergold.application.ports.in.user.SaveUserUseCase;
import com.mistergold.mistergold.application.ports.out.user.SaveUserPort;
import com.mistergold.mistergold.application.ports.out.user.SearchUserPort;
import com.mistergold.mistergold.configuration.web.advice.exception.DataIntegratyViolationException;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;

import lombok.RequiredArgsConstructor;

import java.time.Instant;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveUserService implements SaveUserUseCase {
    private final SearchUserPort searchUserPort;
    private final SaveUserPort saveUserPort;

    @Override
    public User save(User user) {
        if (searchUserPort.checkEmailExists(user.getEmail())) throw new DataIntegratyViolationException(RunErrorEnum.ERR0002);

        InfoActivation infoActivation = InfoActivation.builder()
            .creationDate(Instant.now())
            .isActive(true)
            .build();

        user.setInfoActivation(infoActivation);

        return saveUserPort.save(user);
    }
}
