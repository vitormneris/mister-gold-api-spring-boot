package com.mistergold.mistergold.adapters.persistence.mappers;

import com.mistergold.mistergold.adapters.persistence.entities.InfoActivationEntity;
import com.mistergold.mistergold.adapters.persistence.entities.product.ProductEntity;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.product.Product;
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
public class ProductPersistenceMapperImpl implements ProductPersistenceMapper {

    @Override
    public Set<Product> mapListToDomain(Set<ProductEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        Set<Product> set = new LinkedHashSet<Product>( Math.max( (int) ( entities.size() / .75f ) + 1, 16 ) );
        for ( ProductEntity productEntity : entities ) {
            set.add( mapToDomain( productEntity ) );
        }

        return set;
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
