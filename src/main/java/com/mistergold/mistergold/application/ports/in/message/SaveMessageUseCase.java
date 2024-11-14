package com.mistergold.mistergold.application.ports.in.message;

import com.mistergold.mistergold.application.domain.message.Message;

public interface SaveMessageUseCase {
    Message save(Message message);
}
