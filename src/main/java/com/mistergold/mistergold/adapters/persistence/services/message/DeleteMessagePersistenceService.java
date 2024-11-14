package com.mistergold.mistergold.adapters.persistence.services.message;

import com.mistergold.mistergold.adapters.persistence.repositories.MessageRepository;
import com.mistergold.mistergold.application.ports.out.message.DeleteMessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteMessagePersistenceService implements DeleteMessagePort {
    private final MessageRepository messageRepository;

    @Override
    public void deleteById(String id) {
        messageRepository.deleteById(id);
    }
}
