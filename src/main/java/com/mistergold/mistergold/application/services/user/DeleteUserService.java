package com.mistergold.mistergold.application.services.user;

import com.mistergold.mistergold.application.domain.user.User;
import com.mistergold.mistergold.application.ports.in.user.DeleteUserUseCase;
import com.mistergold.mistergold.application.ports.out.user.DeleteUserPort;
import com.mistergold.mistergold.application.ports.out.user.SaveUserPort;
import com.mistergold.mistergold.application.ports.out.user.SearchUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class DeleteUserService implements DeleteUserUseCase {
    private final SaveUserPort saveUserPort;
    private final DeleteUserPort deleteUserPort;
    private final SearchUserPort searchUserPort;

    @Override
    public void deleteById(String id) {
        deleteUserPort.deleteById(id);
    }

    @Override
    public User inactivate(String id) {
        User user = searchUserPort.findById(id);
        user.getInfoActivation().setIsActive(false);
        user.getInfoActivation().setDeactivationDate(Instant.now());
        return saveUserPort.save(user);
    }
}
