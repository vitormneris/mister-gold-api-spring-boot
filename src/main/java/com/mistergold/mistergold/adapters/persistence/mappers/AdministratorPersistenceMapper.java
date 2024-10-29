package com.mistergold.mistergold.adapters.persistence.mappers;

import com.mistergold.mistergold.adapters.persistence.entities.InfoActivationEntity;
import com.mistergold.mistergold.adapters.persistence.entities.administrator.AdministratorEntity;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.administrator.Administrator;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdministratorPersistenceMapper {
    List<Administrator> mapListToDomain(List<AdministratorEntity> entities);
    AdministratorEntity mapToEntity(Administrator administrator);
    Administrator mapToDomain(AdministratorEntity administratorEntity);
    InfoActivationEntity mapToEntity(InfoActivation infoActivation);
    InfoActivation mapToDomain(InfoActivationEntity infoActivationEntity);
}
