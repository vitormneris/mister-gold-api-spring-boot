package com.mistergold.mistergold.application.services.message;

import com.mistergold.mistergold.application.ports.in.message.DeleteMessageUseCase;
import com.mistergold.mistergold.application.ports.out.message.DeleteMessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteMessageService implements DeleteMessageUseCase {
    private final DeleteMessagePort deleteMessagePort;

    @Override
    public void deleteById(String id) {
        deleteMessagePort.deleteById(id);
    }
}
