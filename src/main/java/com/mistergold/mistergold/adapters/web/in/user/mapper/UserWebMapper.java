package com.mistergold.mistergold.adapters.web.in.user.mapper;

import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import com.mistergold.mistergold.adapters.web.in.user.dto.UserDTO;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.user.User;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserWebMapper {
    List<UserDTO> mapToDTO(List<User> user);
    UserDTO mapToDTO(User user);
    User mapToDomain(UserDTO userDTO);
    InfoActivationDTO mapToDTO(InfoActivation infoActivation);
    InfoActivation mapToDomain(InfoActivationDTO infoActivation);
}
