package com.mistergold.mistergold.adapters.persistence.mappers;

import com.mistergold.mistergold.adapters.persistence.entities.InfoActivationEntity;
import com.mistergold.mistergold.adapters.persistence.entities.user.UserEntity;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {
    UserEntity mapToEntity(User user);
    User mapToDomain(UserEntity userEntity);
    InfoActivationEntity mapToEntity(InfoActivation infoActivation);
    InfoActivation mapToDomain(InfoActivationEntity infoActivationEntity);
}
