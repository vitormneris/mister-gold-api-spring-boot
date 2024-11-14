package com.mistergold.mistergold.adapters.persistence.services.message;

import com.mistergold.mistergold.adapters.persistence.entities.message.MessageEntity;
import com.mistergold.mistergold.adapters.persistence.mappers.MessagePersistenceMapper;
import com.mistergold.mistergold.adapters.persistence.repositories.MessageRepository;
import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.message.Message;
import com.mistergold.mistergold.application.ports.out.message.SearchMessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SearchMessagePersistenceService implements SearchMessagePort {
    private final MessageRepository messageRepository;
    private final MessagePersistenceMapper mapper;

    @Override
    public Set<Message> findAll() {
        return mapper.mapToListDomain(new HashSet<>(messageRepository.findAll()));

    }
}
