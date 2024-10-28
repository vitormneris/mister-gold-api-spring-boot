package com.mistergold.mistergold.adapters.persistence.repositories;

import com.mistergold.mistergold.adapters.persistence.entities.category.CategoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<CategoryEntity, String> {
}
