package com.mistergold.mistergold.application.ports.in.user;

import com.mistergold.mistergold.application.domain.user.User;

public interface UpdateUserUseCase {
    User update(User user, String id);
}
