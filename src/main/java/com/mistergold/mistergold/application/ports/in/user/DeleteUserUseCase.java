package com.mistergold.mistergold.application.ports.in.user;

import com.mistergold.mistergold.application.domain.user.User;

public interface DeleteUserUseCase {
    void deleteById(String id);
    User inactivate(String id);
}
