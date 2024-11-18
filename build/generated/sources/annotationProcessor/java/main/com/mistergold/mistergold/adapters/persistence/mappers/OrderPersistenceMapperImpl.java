package com.mistergold.mistergold.adapters.persistence.mappers;

import com.mistergold.mistergold.adapters.persistence.entities.InfoActivationEntity;
import com.mistergold.mistergold.adapters.persistence.entities.order.OrderEntity;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.order.Order;
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
public class OrderPersistenceMapperImpl implements OrderPersistenceMapper {

    @Override
    public List<Order> mapListToDomain(List<OrderEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<Order> list = new ArrayList<Order>( entities.size() );
        for ( OrderEntity orderEntity : entities ) {
            list.add( mapToDomain( orderEntity ) );
        }

        return list;
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
