package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.model.dto.BrandDto;
import dev.oleksa.sportshop.model.dto.CategoryDto;
import dev.oleksa.sportshop.model.dto.DiscountDto;
import dev.oleksa.sportshop.model.dto.ProductDto;
import dev.oleksa.sportshop.model.product.Brand;
import dev.oleksa.sportshop.model.product.Category;
import dev.oleksa.sportshop.model.product.Discount;
import dev.oleksa.sportshop.model.product.Product;
import dev.oleksa.sportshop.repository.*;
import dev.oleksa.sportshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final DiscountRepository discountRepository;
    private final UserRepository userRepository;

    @Override
    public void createProduct(ProductDto product) {
        productRepository.save(Product.builder()
                .category(readCategory(product.getCategoryId()))
                .discount(readDiscount(product.getDiscountId()))
                .brand(readBrand(product.getBrandId()))
                .nameUa(product.getNameUa())
                .nameEng(product.getNameEng())
                .descriptionUa(product.getDescriptionUa())
                .descriptionEng(product.getDescriptionEng())
                .imageUrl(product.getImageUrl())
                .build()
        );
    }

    @Override
    public Product readProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }

    @Override
    public void updateProduct(ProductDto product, Long productId) {
        Product updatedProduct = this.readProduct(productId);
        updatedProduct.setCategory(readCategory(product.getCategoryId()));
        updatedProduct.setDiscount(readDiscount(product.getDiscountId()));
        updatedProduct.setBrand(readBrand(product.getBrandId()));
        updatedProduct.setNameUa(product.getNameUa());
        updatedProduct.setNameEng(product.getNameEng());
        updatedProduct.setDescriptionUa(product.getDescriptionUa());
        updatedProduct.setDescriptionEng(product.getDescriptionEng());
        updatedProduct.setImageUrl(product.getImageUrl());
        productRepository.save(updatedProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public void addCategoryForProduct(Long categoryId, Long productId) {
        Product product = this.readProduct(productId);
        product.setCategory(this.readCategory(categoryId));
        // todo

    }

    @Override
    public void deleteCategoryFromProduct(Long categoryId, Long productId) {

    }

    @Override
    public void createBrand(BrandDto brand) {
        brandRepository.save(Brand.builder()
                .name(brand.getName())
                .logoImageUrl(brand.getLogoImageUrl())
                .build()
        );
    }

    @Override
    public Brand readBrand(Long brandId) {
        return brandRepository.findById(brandId).orElseThrow();
    }

    @Override
    public void updateBrand(BrandDto brand, Long brandId) {
        Brand updatedBrand = this.readBrand(brandId);
        updatedBrand.setName(brand.getName());
        updatedBrand.setLogoImageUrl(brand.getLogoImageUrl());
        brandRepository.save(updatedBrand);
    }

    @Override
    public void deleteBrand(Long brandId) {
        brandRepository.deleteById(brandId);
    }

    @Override
    public void createCategory(CategoryDto category) {
        categoryRepository.save(Category.builder()
                .category(readCategory(category.getParentCategoryId()))
                .nameUa(category.getNameUa())
                .nameEng(category.getNameEng())
                .imageUrl(category.getImageUrl())
                .build()
        );
    }

    @Override
    public Category readCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow();
    }

    @Override
    public void updateCategory(CategoryDto category, Long categoryId) {
        Category updatedCategory = this.readCategory(categoryId);
        updatedCategory.setCategory(readCategory(category.getParentCategoryId()));
        updatedCategory.setNameUa(category.getNameUa());
        updatedCategory.setNameEng(category.getNameEng());
        updatedCategory.setImageUrl(category.getImageUrl());
        categoryRepository.save(updatedCategory);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public void createDiscount(DiscountDto discount) {
        discountRepository.save(Discount.builder()
                .nameUa(discount.getNameUa())
                .nameEng(discount.getNameEng())
                .descriptionUa(discount.getDescriptionUa())
                .descriptionEng(discount.getDescriptionEng())
                .discountPercent(discount.getDiscountPercent())
                .build()
        );
    }

    @Override
    public Discount readDiscount(Long discountId) {
        return discountRepository.findById(discountId).orElseThrow();
    }

    @Override
    public void updateDiscount(DiscountDto discount, Long discountId) {
        Discount updatedDiscount = this.readDiscount(discountId);
        updatedDiscount.setNameUa(discount.getNameUa());
        updatedDiscount.setNameEng(discount.getNameEng());
        updatedDiscount.setDescriptionUa(discount.getDescriptionUa());
        updatedDiscount.setDescriptionEng(discount.getDescriptionEng());
        updatedDiscount.setDiscountPercent(discount.getDiscountPercent());
        discountRepository.save(updatedDiscount);

    }

    @Override
    public void deleteDiscount(Long discountId) {
        discountRepository.deleteById(discountId);
    }
}
