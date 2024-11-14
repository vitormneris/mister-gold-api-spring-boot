package com.mistergold.mistergold.application.ports.out.message;

import com.mistergold.mistergold.application.domain.message.Message;

import java.util.Set;

public interface SearchMessagePort {
    Set<Message> findAll();
}
