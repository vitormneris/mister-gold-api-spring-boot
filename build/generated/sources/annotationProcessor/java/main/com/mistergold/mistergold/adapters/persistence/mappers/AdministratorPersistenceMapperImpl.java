package com.mistergold.mistergold.adapters.persistence.mappers;

import com.mistergold.mistergold.adapters.persistence.entities.InfoActivationEntity;
import com.mistergold.mistergold.adapters.persistence.entities.administrator.AdministratorEntity;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.administrator.Administrator;
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
public class AdministratorPersistenceMapperImpl implements AdministratorPersistenceMapper {

    @Override
    public Set<Administrator> mapListToDomain(Set<AdministratorEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        Set<Administrator> set = new LinkedHashSet<Administrator>( Math.max( (int) ( entities.size() / .75f ) + 1, 16 ) );
        for ( AdministratorEntity administratorEntity : entities ) {
            set.add( mapToDomain( administratorEntity ) );
        }

        return set;
    }

    @Override
    public AdministratorEntity mapToEntity(Administrator administrator) {
        if ( administrator == null ) {
            return null;
        }

        AdministratorEntity administratorEntity = new AdministratorEntity();

        administratorEntity.setId( administrator.getId() );
        administratorEntity.setName( administrator.getName() );
        administratorEntity.setEmail( administrator.getEmail() );
        administratorEntity.setPassword( administrator.getPassword() );
        administratorEntity.setRole( administrator.getRole() );
        administratorEntity.setCode( administrator.getCode() );
        administratorEntity.setInfoActivation( mapToEntity( administrator.getInfoActivation() ) );

        return administratorEntity;
    }

    @Override
    public Administrator mapToDomain(AdministratorEntity administratorEntity) {
        if ( administratorEntity == null ) {
            return null;
        }

        Administrator administrator = new Administrator();

        administrator.setId( administratorEntity.getId() );
        administrator.setName( administratorEntity.getName() );
        administrator.setEmail( administratorEntity.getEmail() );
        administrator.setPassword( administratorEntity.getPassword() );
        administrator.setCode( administratorEntity.getCode() );
        administrator.setRole( administratorEntity.getRole() );
        administrator.setInfoActivation( mapToDomain( administratorEntity.getInfoActivation() ) );

        return administrator;
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
