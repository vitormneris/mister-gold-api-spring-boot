package com.mistergold.mistergold.adapters.web.in.message.mapper;

import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import com.mistergold.mistergold.adapters.web.in.message.dto.MessageDTO;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.message.Message;
import java.time.Instant;
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
public class MessageWebMapperImpl implements MessageWebMapper {

    @Override
    public Set<MessageDTO> mapToListDTO(Set<Message> message) {
        if ( message == null ) {
            return null;
        }

        Set<MessageDTO> set = new LinkedHashSet<MessageDTO>( Math.max( (int) ( message.size() / .75f ) + 1, 16 ) );
        for ( Message message1 : message ) {
            set.add( mapToDTO( message1 ) );
        }

        return set;
    }

    @Override
    public MessageDTO mapToDTO(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageDTO.MessageDTOBuilder messageDTO = MessageDTO.builder();

        messageDTO.id( message.getId() );
        messageDTO.senderName( message.getSenderName() );
        messageDTO.senderEmail( message.getSenderEmail() );
        messageDTO.text( message.getText() );
        messageDTO.infoActivation( mapToDTO( message.getInfoActivation() ) );

        return messageDTO.build();
    }

    @Override
    public Message mapToDomain(MessageDTO messageDTO) {
        if ( messageDTO == null ) {
            return null;
        }

        Message.MessageBuilder message = Message.builder();

        message.id( messageDTO.id() );
        message.senderName( messageDTO.senderName() );
        message.senderEmail( messageDTO.senderEmail() );
        message.text( messageDTO.text() );
        message.infoActivation( mapToDomain( messageDTO.infoActivation() ) );

        return message.build();
    }

    @Override
    public InfoActivationDTO mapToDTO(InfoActivation infoActivation) {
        if ( infoActivation == null ) {
            return null;
        }

        Boolean isActive = null;
        Instant creationDate = null;
        Instant deactivationDate = null;

        isActive = infoActivation.getIsActive();
        creationDate = infoActivation.getCreationDate();
        deactivationDate = infoActivation.getDeactivationDate();

        InfoActivationDTO infoActivationDTO = new InfoActivationDTO( isActive, creationDate, deactivationDate );

        return infoActivationDTO;
    }

    @Override
    public InfoActivation mapToDomain(InfoActivationDTO infoActivation) {
        if ( infoActivation == null ) {
            return null;
        }

        InfoActivation.InfoActivationBuilder infoActivation1 = InfoActivation.builder();

        infoActivation1.isActive( infoActivation.isActive() );
        infoActivation1.creationDate( infoActivation.creationDate() );
        infoActivation1.deactivationDate( infoActivation.deactivationDate() );

        return infoActivation1.build();
    }
}
