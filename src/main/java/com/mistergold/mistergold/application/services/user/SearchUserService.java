package com.mistergold.mistergold.application.services.user;

import com.mistergold.mistergold.application.domain.user.User;
import com.mistergold.mistergold.application.ports.in.user.SearchUserUseCase;
import com.mistergold.mistergold.application.ports.out.user.SearchUserPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchUserService implements SearchUserUseCase {
    private final SearchUserPort searchUserPort;

    @Override
    public User findById(String id) {
        return searchUserPort.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return searchUserPort.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return searchUserPort.findAll();
    }
}
