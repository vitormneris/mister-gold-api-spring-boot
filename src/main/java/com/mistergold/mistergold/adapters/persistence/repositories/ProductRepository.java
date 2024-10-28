package com.mistergold.mistergold.adapters.persistence.repositories;

import com.mistergold.mistergold.adapters.persistence.entities.product.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, String> {
}
