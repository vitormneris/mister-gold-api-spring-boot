package com.mistergold.mistergold.adapters.persistence.mappers;

import com.mistergold.mistergold.adapters.persistence.entities.InfoActivationEntity;
import com.mistergold.mistergold.adapters.persistence.entities.product.ProductEntity;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.product.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductPersistenceMapper {
    List<Product> mapListToDomain(List<ProductEntity> entities);
    ProductEntity mapToEntity(Product product);
    Product mapToDomain(ProductEntity productEntity);
    InfoActivation mapToDomain(InfoActivationEntity infoActivationEntity);
}
