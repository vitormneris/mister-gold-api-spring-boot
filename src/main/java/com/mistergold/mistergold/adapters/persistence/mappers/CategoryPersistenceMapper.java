package com.mistergold.mistergold.adapters.persistence.mappers;

import com.mistergold.mistergold.adapters.persistence.entities.InfoActivationEntity;
import com.mistergold.mistergold.adapters.persistence.entities.category.CategoryEntity;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.category.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryPersistenceMapper {
    List<Category> mapListToDomain(List<CategoryEntity> entities);
    CategoryEntity mapToEntity(Category category);
    Category mapToDomain(CategoryEntity categoryEntity);
    InfoActivation mapToDomain(InfoActivationEntity infoActivationEntity);
}
