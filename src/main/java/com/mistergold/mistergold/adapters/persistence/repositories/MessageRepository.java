package com.mistergold.mistergold.adapters.persistence.repositories;

import com.mistergold.mistergold.adapters.persistence.entities.message.MessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<MessageEntity, String> {
}
