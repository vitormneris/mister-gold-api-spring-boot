package com.mistergold.mistergold.application.services.user;

import com.mistergold.mistergold.application.domain.user.User;
import com.mistergold.mistergold.application.ports.in.user.SaveUserUseCase;
import com.mistergold.mistergold.application.ports.out.user.SaveUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveUserService implements SaveUserUseCase {
    private final SaveUserPort saveUserPort;

    @Override
    public User save(User user) {
        return saveUserPort.save(user);
    }
}
