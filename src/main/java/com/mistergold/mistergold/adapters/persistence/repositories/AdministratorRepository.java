package com.mistergold.mistergold.adapters.persistence.repositories;

import com.mistergold.mistergold.adapters.persistence.entities.administrator.AdministratorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministratorRepository extends MongoRepository<AdministratorEntity, String> {
    Optional<AdministratorEntity> findByEmail(String email);
    @Query("{'infoActivation.isActive': ?0, $or: [ { 'name': { $regex: ?1, $options: 'i' } }, { 'name': { $exists: false } } ] }")
    Page<AdministratorEntity> findByPagination(Boolean isActive, Pageable pageable, String name);
}
