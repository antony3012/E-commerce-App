package com.ecommerce.product_service.Mapper;

import com.ecommerce.product_service.Dto.ProductDTO;
import com.ecommerce.product_service.Entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDto(Product product);
    Product toEntity(ProductDTO dto);

    List<ProductDTO> toDtoList(List<Product> productList);

    @Mapping(target = "id", ignore = true) // ID should not be updated from DTO
    void updateProductFromDto(ProductDTO dto, @MappingTarget Product entity);
}
