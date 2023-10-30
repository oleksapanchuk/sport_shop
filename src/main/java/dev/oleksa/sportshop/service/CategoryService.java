package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.dto.CategoryDto;
import dev.oleksa.sportshop.model.product.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    List<Category> getCategoriesByParentId(Long parentCategoryId);

    Category getCategoryById(Long categoryId);

    Category createCategory(CategoryDto categoryDto);

    Category updateCategory(Long categoryId, CategoryDto categoryDto);

    Boolean deleteCategory(Long categoryId);
}
