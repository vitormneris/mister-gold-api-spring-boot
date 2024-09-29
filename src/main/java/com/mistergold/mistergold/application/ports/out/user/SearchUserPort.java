package com.mistergold.mistergold.application.ports.out.user;

import java.util.List;

import com.mistergold.mistergold.application.domain.user.User;

public interface SearchUserPort {
    List<User> findAll();
    User findById(String id);
    User findByEmail(String email);
    Boolean checkEmailExists(String email);
}
