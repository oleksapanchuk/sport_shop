package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.model.dto.ProductItemDto;

public interface ProductItemService {
    ProductItemDto createProductItem(ProductItemDto productItemDto);

    ProductItemDto readProductItem(Long productItemId);

    ProductItemDto updateProductItem(ProductItemDto productItemDto);

    Boolean deleteProductItem(Long productItemId);
}
