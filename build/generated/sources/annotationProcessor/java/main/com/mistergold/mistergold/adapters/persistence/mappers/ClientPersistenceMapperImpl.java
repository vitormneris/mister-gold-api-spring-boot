package com.mistergold.mistergold.adapters.persistence.mappers;

import com.mistergold.mistergold.adapters.persistence.entities.InfoActivationEntity;
import com.mistergold.mistergold.adapters.persistence.entities.client.AddressEntity;
import com.mistergold.mistergold.adapters.persistence.entities.client.ClientEntity;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.client.Address;
import com.mistergold.mistergold.application.domain.client.Client;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T11:09:51-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 21.0.5 (Ubuntu)"
)
@Component
public class ClientPersistenceMapperImpl implements ClientPersistenceMapper {

    @Override
    public List<Client> mapListToDomain(List<ClientEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<Client> list = new ArrayList<Client>( entities.size() );
        for ( ClientEntity clientEntity : entities ) {
            list.add( mapToDomain( clientEntity ) );
        }

        return list;
    }

    @Override
    public Address mapToDomain(AddressEntity addressEntity) {
        if ( addressEntity == null ) {
            return null;
        }

        Address address = new Address();

        address.setState( addressEntity.getState() );
        address.setCity( addressEntity.getCity() );
        address.setNeighborhood( addressEntity.getNeighborhood() );
        address.setStreet( addressEntity.getStreet() );
        address.setPostalCode( addressEntity.getPostalCode() );
        address.setNumber( addressEntity.getNumber() );
        address.setComplement( addressEntity.getComplement() );

        return address;
    }

    @Override
    public AddressEntity mapToEntity(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressEntity.AddressEntityBuilder addressEntity = AddressEntity.builder();

        addressEntity.state( address.getState() );
        addressEntity.city( address.getCity() );
        addressEntity.neighborhood( address.getNeighborhood() );
        addressEntity.street( address.getStreet() );
        addressEntity.postalCode( address.getPostalCode() );
        addressEntity.number( address.getNumber() );
        addressEntity.complement( address.getComplement() );

        return addressEntity.build();
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
