package com.mistergold.mistergold.adapters.persistence.services.message;

import com.mistergold.mistergold.adapters.persistence.mappers.MessagePersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.MessageRepository;
import com.mistergold.mistergold.application.ports.out.message.SaveMessagePort;
import com.mistergold.mistergold.application.domain.message.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveMessagePersistenceService implements SaveMessagePort {
    private final MessageRepository messageRepository;
    private final MessagePersistenceMapper mapper;

    @Override
    public Message save(Message message) {
        return mapper.mapToDomain(messageRepository.save(mapper.mapToEntity(message)));
    }
}
