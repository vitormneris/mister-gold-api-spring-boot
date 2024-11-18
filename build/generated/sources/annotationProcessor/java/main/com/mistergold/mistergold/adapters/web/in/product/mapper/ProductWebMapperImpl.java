package com.mistergold.mistergold.adapters.web.in.product.mapper;

import com.mistergold.mistergold.adapters.web.PageResponseDTO;
import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import com.mistergold.mistergold.adapters.web.in.product.dto.ProductDTO;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.product.Product;
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
public class ProductWebMapperImpl implements ProductWebMapper {

    @Override
    public List<ProductDTO> mapToListDTO(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDTO> list = new ArrayList<ProductDTO>( products.size() );
        for ( Product product : products ) {
            list.add( mapToDTO( product ) );
        }

        return list;
    }

    @Override
    public PageResponseDTO<ProductDTO> mapToPageResponseDto(PageResponse<Product> pageResponse) {
        if ( pageResponse == null ) {
            return null;
        }

        PageResponseDTO<ProductDTO> pageResponseDTO = new PageResponseDTO<ProductDTO>();

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
        pageResponseDTO.setContent( productSetToProductDTOSet( pageResponse.getContent() ) );

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

    protected Set<ProductDTO> productSetToProductDTOSet(Set<Product> set) {
        if ( set == null ) {
            return null;
        }

        Set<ProductDTO> set1 = new LinkedHashSet<ProductDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Product product : set ) {
            set1.add( mapToDTO( product ) );
        }

        return set1;
    }
}
