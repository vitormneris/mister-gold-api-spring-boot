package com.mistergold.mistergold.adapters.web.in.administrator.mapper;

import com.mistergold.mistergold.adapters.web.PageResponseDTO;
import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import com.mistergold.mistergold.adapters.web.in.administrator.dto.AdministratorDTO;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.administrator.Administrator;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdministratorWebMapper {
    List<AdministratorDTO> mapToDTO(List<Administrator> administrators);
    AdministratorDTO mapToDTO(Administrator administrator);
    Administrator mapToDomain(AdministratorDTO administratorDTO);
    PageResponseDTO<AdministratorDTO> mapToPageResponseDto(PageResponse<Administrator> pageResponse);
    InfoActivationDTO mapToDTO(InfoActivation infoActivation);
    InfoActivation mapToDomain(InfoActivationDTO infoActivation);
}
