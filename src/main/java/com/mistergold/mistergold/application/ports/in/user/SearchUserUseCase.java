package com.mistergold.mistergold.application.ports.in.user;

import com.mistergold.mistergold.application.domain.user.User;

public interface SearchUserUseCase {
    User findById(String id);
    User findByEmail(String email);
}
