package com.elitetech.springsecurity.service.interfaces;

import com.elitetech.springsecurity.dto.ProductDTO;
import java.util.List;

public interface ProductService {
    ProductDTO addProduct(ProductDTO productDTO);

    ProductDTO updateProduct(Long id, ProductDTO productDTO);

    void deleteProduct(Long id);

    ProductDTO getProductById(Long id);

    List<ProductDTO> getAllProducts();
}
