package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.model.dto.ProductDto;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    ProductDto readProduct(Long productId);

    ProductDto updateProduct(ProductDto productDto);

    Boolean deleteProduct(Long productId);

}
