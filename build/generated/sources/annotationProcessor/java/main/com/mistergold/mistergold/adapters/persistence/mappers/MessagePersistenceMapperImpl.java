package com.mistergold.mistergold.adapters.persistence.mappers;

import com.mistergold.mistergold.adapters.persistence.entities.InfoActivationEntity;
import com.mistergold.mistergold.adapters.persistence.entities.message.MessageEntity;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.message.Message;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T11:09:51-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 21.0.5 (Ubuntu)"
)
@Component
public class MessagePersistenceMapperImpl implements MessagePersistenceMapper {

    @Override
    public Set<Message> mapToListDomain(Set<MessageEntity> messageEntities) {
        if ( messageEntities == null ) {
            return null;
        }

        Set<Message> set = new LinkedHashSet<Message>( Math.max( (int) ( messageEntities.size() / .75f ) + 1, 16 ) );
        for ( MessageEntity messageEntity : messageEntities ) {
            set.add( mapToDomain( messageEntity ) );
        }

        return set;
    }

    @Override
    public Message mapToDomain(MessageEntity messageEntity) {
        if ( messageEntity == null ) {
            return null;
        }

        Message.MessageBuilder message = Message.builder();

        message.id( messageEntity.getId() );
        message.senderName( messageEntity.getSenderName() );
        message.senderEmail( messageEntity.getSenderEmail() );
        message.text( messageEntity.getText() );
        message.infoActivation( mapToDomain( messageEntity.getInfoActivation() ) );

        return message.build();
    }

    @Override
    public MessageEntity mapToEntity(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageEntity.MessageEntityBuilder messageEntity = MessageEntity.builder();

        messageEntity.id( message.getId() );
        messageEntity.senderName( message.getSenderName() );
        messageEntity.senderEmail( message.getSenderEmail() );
        messageEntity.text( message.getText() );
        messageEntity.infoActivation( mapToEntity( message.getInfoActivation() ) );

        return messageEntity.build();
    }

    @Override
    public InfoActivationEntity mapToEntity(InfoActivation infoActivation) {
        if ( infoActivation == null ) {
            return null;
        }

        InfoActivationEntity infoActivationEntity = new InfoActivationEntity();

        infoActivationEntity.setIsActive( infoActivation.getIsActive() );
        infoActivationEntity.setCreationDate( infoActivation.getCreationDate() );
        infoActivationEntity.setDeactivationDate( infoActivation.getDeactivationDate() );

        return infoActivationEntity;
    }

    @Override
    public InfoActivation mapToDomain(InfoActivationEntity infoActivationEntity) {
        if ( infoActivationEntity == null ) {
            return null;
        }

        InfoActivation.InfoActivationBuilder infoActivation = InfoActivation.builder();

        infoActivation.isActive( infoActivationEntity.getIsActive() );
        infoActivation.creationDate( infoActivationEntity.getCreationDate() );
        infoActivation.deactivationDate( infoActivationEntity.getDeactivationDate() );

        return infoActivation.build();
    }
}
