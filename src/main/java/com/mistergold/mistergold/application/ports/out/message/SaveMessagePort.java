package com.mistergold.mistergold.application.ports.out.message;

import com.mistergold.mistergold.application.domain.message.Message;

public interface SaveMessagePort {
    Message save(Message message);
}
