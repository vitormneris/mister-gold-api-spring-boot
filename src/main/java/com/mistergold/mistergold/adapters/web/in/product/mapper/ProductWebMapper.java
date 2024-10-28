package com.mistergold.mistergold.adapters.web.in.product.mapper;

import com.mistergold.mistergold.adapters.web.in.InfoActivationDTO;
import com.mistergold.mistergold.adapters.web.in.product.dto.ProductDTO;
import com.mistergold.mistergold.application.domain.InfoActivation;
import com.mistergold.mistergold.application.domain.product.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductWebMapper {
    List<ProductDTO> mapToDTO(List<Product> client);
    ProductDTO mapToDTO(Product client);
    Product mapToDomain(ProductDTO clientDTO);
    InfoActivationDTO mapToDTO(InfoActivation infoActivation);
    InfoActivation mapToDomain(InfoActivationDTO infoActivation);
}
