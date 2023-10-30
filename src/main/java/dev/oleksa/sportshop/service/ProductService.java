package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.dto.ProductDto;
import dev.oleksa.sportshop.model.product.Product;
import dev.oleksa.sportshop.model.product.ProductColor;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Page<Product> getProducts(int size, int page, String sortByField, List<String> filterFields);
    ProductDto readProduct(Long productId);
    ProductDto updateProduct(ProductDto productDto);
    Boolean deleteProduct(Long productId);

}
