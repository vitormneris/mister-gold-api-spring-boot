package com.mistergold.mistergold.adapters.web.in.message.mapper;

import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import com.mistergold.mistergold.adapters.web.in.message.dto.MessageDTO;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.message.Message;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface MessageWebMapper {
    Set<MessageDTO> mapToListDTO(Set<Message> message);
    MessageDTO mapToDTO(Message message);
    Message mapToDomain(MessageDTO messageDTO);
    InfoActivationDTO mapToDTO(InfoActivation infoActivation);
    InfoActivation mapToDomain(InfoActivationDTO infoActivation);
}
