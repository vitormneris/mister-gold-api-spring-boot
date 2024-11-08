package com.mistergold.mistergold.adapters.persistence.repositories;

import com.mistergold.mistergold.adapters.persistence.entities.product.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, String> {
    @Query("{'infoActivation.isActive': ?0, $or: [ { 'name': { $regex: ?1, $options: 'i' } }, { 'name': { $exists: false } } ] }")
    Page<ProductEntity> findByPagination(Boolean isActive, Pageable pageable, String name);
}
