package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.model.dto.BrandDto;
import dev.oleksa.sportshop.model.dto.CategoryDto;
import dev.oleksa.sportshop.model.dto.DiscountDto;
import dev.oleksa.sportshop.model.dto.ProductDto;
import dev.oleksa.sportshop.model.product.Brand;
import dev.oleksa.sportshop.model.product.Category;
import dev.oleksa.sportshop.model.product.Discount;
import dev.oleksa.sportshop.model.product.Product;

public interface ProductService {
    void createProduct(ProductDto product);
    Product readProduct(Long productId);
    void updateProduct(ProductDto product, Long productId);
    void deleteProduct(Long productId);
    void addCategoryForProduct(Long categoryId, Long productId);
    void deleteCategoryFromProduct(Long categoryId, Long productId);

    void createBrand(BrandDto brand);
    Brand readBrand(Long brandId);
    void updateBrand(BrandDto brand, Long brandId);
    void deleteBrand(Long brandId);

    void createCategory(CategoryDto category);
    Category readCategory(Long categoryId);
    void updateCategory(CategoryDto category, Long categoryId);
    void deleteCategory(Long categoryId);

    void createDiscount(DiscountDto discount);
    Discount readDiscount(Long discountId);
    void updateDiscount(DiscountDto discount, Long discountId);
    void deleteDiscount(Long discountId);

}
