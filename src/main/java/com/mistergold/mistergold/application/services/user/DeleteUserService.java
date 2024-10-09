package com.mistergold.mistergold.application.services.user;

import com.mistergold.mistergold.application.domain.user.User;
import com.mistergold.mistergold.application.ports.in.user.DeleteUserUseCase;
import com.mistergold.mistergold.application.ports.out.user.DeleteUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DeleteUserService implements DeleteUserUseCase {
    private final DeleteUserPort deleteUserPort;

    @Override
    public void delete(String id) {
        deleteUserPort.delete(id);
    }

    @Override
    public User inactivate(String id) {
        return deleteUserPort.inactivate(id);
    }
}
