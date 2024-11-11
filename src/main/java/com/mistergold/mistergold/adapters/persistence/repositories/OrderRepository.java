package com.mistergold.mistergold.adapters.persistence.repositories;

import com.mistergold.mistergold.adapters.persistence.entities.order.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {
    @Query("{'infoActivation.isActive': ?0, $or: [ { 'name': { $regex: ?1, $options: 'i' } }, { 'name': { $exists: false } } ] }")
    Page<OrderEntity> findByPagination(Boolean isActive, Pageable pageable, String name);
}
