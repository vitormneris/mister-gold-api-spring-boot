package com.mistergold.mistergold.application.ports.out.user;

import com.mistergold.mistergold.application.domain.user.User;

public interface UpdateUserPort {
    User update(User user, String id);
}
