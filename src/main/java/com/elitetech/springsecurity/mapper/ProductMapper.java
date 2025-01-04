package com.elitetech.springsecurity.mapper;

import org.modelmapper.ModelMapper;

import com.elitetech.springsecurity.dto.ProductDTO;
import com.elitetech.springsecurity.entity.Product;

public class ProductMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static ProductDTO convertToDto(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    public static Product convertToEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }
}
