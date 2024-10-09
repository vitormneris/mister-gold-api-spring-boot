package com.mistergold.mistergold.application.services.user;

import com.mistergold.mistergold.application.domain.user.User;
import com.mistergold.mistergold.application.ports.in.user.UpdateUserUseCase;
import com.mistergold.mistergold.application.ports.out.user.UpdateUserPort;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserService implements UpdateUserUseCase {
    private final UpdateUserPort updateUserPort;

    @Override
    public User update(User userNew, String id) {
        return updateUserPort.update(userNew, id);
    }
}
