package com.mistergold.mistergold.application.ports.in.message;


import com.mistergold.mistergold.application.domain.message.Message;

import java.util.Set;

public interface SearchMessageUseCase {
    Set<Message> findAll();
}
