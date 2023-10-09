package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.dto.CategoryDto;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto readCategory(Long categoryId);

    CategoryDto updateCategory(CategoryDto categoryDto);

    Boolean deleteCategory(Long categoryId);
}
