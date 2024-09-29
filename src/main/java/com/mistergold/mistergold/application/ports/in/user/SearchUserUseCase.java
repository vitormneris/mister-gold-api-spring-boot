package com.mistergold.mistergold.application.ports.in.user;

import java.util.List;

import com.mistergold.mistergold.application.domain.user.User;

public interface SearchUserUseCase {
    List<User> findAll();
    User findById(String id);
    User findByEmail(String email);
}
