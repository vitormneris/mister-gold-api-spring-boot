package com.mistergold.mistergold.adapters.web.in.administrator.mapper;

import com.mistergold.mistergold.adapters.web.PageResponseDTO;
import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import com.mistergold.mistergold.adapters.web.in.administrator.dto.AdministratorDTO;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.administrator.Administrator;
import com.mistergold.mistergold.configuration.web.enums.UserRoleEnum;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T11:09:51-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 21.0.5 (Ubuntu)"
)
@Component
public class AdministratorWebMapperImpl implements AdministratorWebMapper {

    @Override
    public List<AdministratorDTO> mapToDTO(List<Administrator> administrators) {
        if ( administrators == null ) {
            return null;
        }

        List<AdministratorDTO> list = new ArrayList<AdministratorDTO>( administrators.size() );
        for ( Administrator administrator : administrators ) {
            list.add( mapToDTO( administrator ) );
        }

        return list;
    }

    @Override
    public AdministratorDTO mapToDTO(Administrator administrator) {
        if ( administrator == null ) {
            return null;
        }

        String id = null;
        String name = null;
        String email = null;
        UserRoleEnum role = null;
        String password = null;
        InfoActivationDTO infoActivation = null;

        id = administrator.getId();
        name = administrator.getName();
        email = administrator.getEmail();
        role = administrator.getRole();
        password = administrator.getPassword();
        infoActivation = mapToDTO( administrator.getInfoActivation() );

        AdministratorDTO administratorDTO = new AdministratorDTO( id, name, email, role, password, infoActivation );

        return administratorDTO;
    }

    @Override
    public Administrator mapToDomain(AdministratorDTO administratorDTO) {
        if ( administratorDTO == null ) {
            return null;
        }

        Administrator administrator = new Administrator();

        administrator.setId( administratorDTO.id() );
        administrator.setName( administratorDTO.name() );
        administrator.setEmail( administratorDTO.email() );
        administrator.setPassword( administratorDTO.password() );
        administrator.setRole( administratorDTO.role() );
        administrator.setInfoActivation( mapToDomain( administratorDTO.infoActivation() ) );

        return administrator;
    }

    @Override
    public PageResponseDTO<AdministratorDTO> mapToPageResponseDto(PageResponse<Administrator> pageResponse) {
        if ( pageResponse == null ) {
            return null;
        }

        PageResponseDTO<AdministratorDTO> pageResponseDTO = new PageResponseDTO<AdministratorDTO>();

        if ( pageResponse.getPageSize() != null ) {
            pageResponseDTO.setPageSize( pageResponse.getPageSize().longValue() );
        }
        if ( pageResponse.getTotalElements() != null ) {
            pageResponseDTO.setTotalElements( pageResponse.getTotalElements().intValue() );
        }
        if ( pageResponse.getTotalPages() != null ) {
            pageResponseDTO.setTotalPages( pageResponse.getTotalPages() );
        }
        if ( pageResponse.getCurrentPage() != null ) {
            pageResponseDTO.setCurrentPage( pageResponse.getCurrentPage() );
        }
        if ( pageResponse.getNextPage() != null ) {
            pageResponseDTO.setNextPage( pageResponse.getNextPage() );
        }
        if ( pageResponse.getPreviousPage() != null ) {
            pageResponseDTO.setPreviousPage( pageResponse.getPreviousPage() );
        }
        pageResponseDTO.setContent( administratorSetToAdministratorDTOSet( pageResponse.getContent() ) );

        return pageResponseDTO;
    }

    @Override
    public InfoActivationDTO mapToDTO(InfoActivation infoActivation) {
        if ( infoActivation == null ) {
            return null;
        }

        Boolean isActive = null;
        Instant creationDate = null;
        Instant deactivationDate = null;

        isActive = infoActivation.getIsActive();
        creationDate = infoActivation.getCreationDate();
        deactivationDate = infoActivation.getDeactivationDate();

        InfoActivationDTO infoActivationDTO = new InfoActivationDTO( isActive, creationDate, deactivationDate );

        return infoActivationDTO;
    }

    @Override
    public InfoActivation mapToDomain(InfoActivationDTO infoActivation) {
        if ( infoActivation == null ) {
            return null;
        }

        InfoActivation.InfoActivationBuilder infoActivation1 = InfoActivation.builder();

        infoActivation1.isActive( infoActivation.isActive() );
        infoActivation1.creationDate( infoActivation.creationDate() );
        infoActivation1.deactivationDate( infoActivation.deactivationDate() );

        return infoActivation1.build();
    }

    protected Set<AdministratorDTO> administratorSetToAdministratorDTOSet(Set<Administrator> set) {
        if ( set == null ) {
            return null;
        }

        Set<AdministratorDTO> set1 = new LinkedHashSet<AdministratorDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Administrator administrator : set ) {
            set1.add( mapToDTO( administrator ) );
        }

        return set1;
    }
}
