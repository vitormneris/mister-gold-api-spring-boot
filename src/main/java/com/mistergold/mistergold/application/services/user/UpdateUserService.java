package com.mistergold.mistergold.application.services.user;

import com.mistergold.mistergold.application.domain.user.User;
import com.mistergold.mistergold.application.ports.in.user.UpdateUserUseCase;
import com.mistergold.mistergold.application.ports.out.user.SaveUserPort;
import com.mistergold.mistergold.application.ports.out.user.SearchUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserService implements UpdateUserUseCase {
    private final SaveUserPort saveUserPort;
    private final SearchUserPort searchUserPort;

    @Override
    public User update(User userNew, String id) {
        User userOld = searchUserPort.findById(id);
        userOld.setName(userNew.getName() == null ? userOld.getName() : userNew.getName());
        userOld.setEmail(userNew.getEmail() == null ? userOld.getEmail() : userNew.getEmail());
        return saveUserPort.save(userOld);
    }
}
