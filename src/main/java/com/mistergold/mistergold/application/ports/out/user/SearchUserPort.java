package com.mistergold.mistergold.application.ports.out.user;

import com.mistergold.mistergold.application.domain.user.User;

public interface SearchUserPort {
    User findById(String id);
    User findByEmail(String email);
    Boolean checkEmailExists(String email);
}
