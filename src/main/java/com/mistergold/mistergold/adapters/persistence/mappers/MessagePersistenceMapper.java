package com.mistergold.mistergold.adapters.persistence.mappers;

import com.mistergold.mistergold.adapters.persistence.entities.InfoActivationEntity;
import com.mistergold.mistergold.adapters.persistence.entities.message.MessageEntity;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.message.Message;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface MessagePersistenceMapper {
    Set<Message> mapToListDomain(Set<MessageEntity> messageEntities);
    Message mapToDomain(MessageEntity messageEntity);
    MessageEntity mapToEntity(Message message);
    InfoActivationEntity mapToEntity(InfoActivation infoActivation);
    InfoActivation mapToDomain(InfoActivationEntity infoActivationEntity);
}
