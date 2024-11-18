package com.mistergold.mistergold.adapters.web.in.category.mapper;

import com.mistergold.mistergold.adapters.web.PageResponseDTO;
import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import com.mistergold.mistergold.adapters.web.in.category.dto.CategoryDTO;
import com.mistergold.mistergold.adapters.web.in.product.dto.ProductDTO;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.PageResponse;
import com.mistergold.mistergold.application.domain.category.Category;
import com.mistergold.mistergold.application.domain.product.Product;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T11:09:51-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 21.0.5 (Ubuntu)"
)
@Component
public class CategoryWebMapperImpl implements CategoryWebMapper {

    @Override
    public Set<CategoryDTO> mapToListDTO(Set<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        Set<CategoryDTO> set = new LinkedHashSet<CategoryDTO>( Math.max( (int) ( categories.size() / .75f ) + 1, 16 ) );
        for ( Category category : categories ) {
            set.add( mapToDTO( category ) );
        }

        return set;
    }

    @Override
    public Category mapToDomain(CategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.id( categoryDTO.id() );
        category.name( categoryDTO.name() );
        category.imageUrl( categoryDTO.imageUrl() );
        category.description( categoryDTO.description() );
        category.products( productDTOSetToProductSet( categoryDTO.products() ) );
        category.infoActivation( mapToDomain( categoryDTO.infoActivation() ) );

        return category.build();
    }

    @Override
    public PageResponseDTO<CategoryDTO> mapToPageResponseDto(PageResponse<Category> pageResponse) {
        if ( pageResponse == null ) {
            return null;
        }

        PageResponseDTO<CategoryDTO> pageResponseDTO = new PageResponseDTO<CategoryDTO>();

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
        pageResponseDTO.setContent( mapToListDTO( pageResponse.getContent() ) );

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

    protected Set<Category> categoryDTOSetToCategorySet(Set<CategoryDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Category> set1 = new LinkedHashSet<Category>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CategoryDTO categoryDTO : set ) {
            set1.add( mapToDomain( categoryDTO ) );
        }

        return set1;
    }

    protected Product productDTOToProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( productDTO.id() );
        product.name( productDTO.name() );
        product.imageUrl( productDTO.imageUrl() );
        product.description( productDTO.description() );
        product.price( productDTO.price() );
        product.quantity( productDTO.quantity() );
        product.categories( categoryDTOSetToCategorySet( productDTO.categories() ) );
        product.infoActivation( mapToDomain( productDTO.infoActivation() ) );

        return product.build();
    }

    protected Set<Product> productDTOSetToProductSet(Set<ProductDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Product> set1 = new LinkedHashSet<Product>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ProductDTO productDTO : set ) {
            set1.add( productDTOToProduct( productDTO ) );
        }

        return set1;
    }
}
