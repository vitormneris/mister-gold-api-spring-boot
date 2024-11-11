package com.mistergold.mistergold.adapters.persistence.mappers;

import com.mistergold.mistergold.adapters.persistence.entities.InfoActivationEntity;
import com.mistergold.mistergold.adapters.persistence.entities.administrator.AdministratorEntity;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.administrator.Administrator;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AdministratorPersistenceMapper {
    List<Administrator> mapListToDomain(List<AdministratorEntity> entities);
    AdministratorEntity mapToEntity(Administrator administrator);
    Administrator mapToDomain(AdministratorEntity administratorEntity);

    default PageResponse<Administrator> mapToPageResponseDomain(Page<AdministratorEntity> administratorEntities) {
        int previousPage = administratorEntities.hasPrevious() ? administratorEntities.getNumber() - 1 : administratorEntities.getNumber();
        int nextPage = administratorEntities.hasNext() ? administratorEntities.getNumber() + 1 : administratorEntities.getNumber();

        List<Administrator> administrators = administratorEntities.getContent().stream().map(this::mapToDomain).collect(Collectors.toList());

        return PageResponse.<Administrator>builder()
                .pageSize(administratorEntities.getNumberOfElements())
                .totalElements(administratorEntities.getTotalElements())
                .currentPage(administratorEntities.getNumber())
                .previousPage(previousPage)
                .nextPage(nextPage)
                .content(administrators)
                .totalPages(administratorEntities.getTotalPages())
                .build();
    }

    InfoActivationEntity mapToEntity(InfoActivation infoActivation);
    InfoActivation mapToDomain(InfoActivationEntity infoActivationEntity);
}
