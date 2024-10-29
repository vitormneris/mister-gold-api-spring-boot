package com.mistergold.mistergold.adapters.persistence.repositories;

import com.mistergold.mistergold.adapters.persistence.entities.administrator.AdministratorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministratorRepository extends MongoRepository<AdministratorEntity, String> {
    Optional<AdministratorEntity> findByEmail(String email);
}
