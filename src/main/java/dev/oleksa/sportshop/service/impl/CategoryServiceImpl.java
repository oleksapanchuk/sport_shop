package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.dto.CategoryDto;
import dev.oleksa.sportshop.mapper.CategoryMapper;
import dev.oleksa.sportshop.model.product.Category;
import dev.oleksa.sportshop.repository.CategoryRepository;
import dev.oleksa.sportshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getCategoriesByParentId(Long parentCategoryId) {
        return categoryRepository.findAllByCategoryId(parentCategoryId);
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow();
    }

    @Override
    public Category createCategory(CategoryDto categoryDto) {
        var category = categoryMapper.toEntity(categoryDto);

        category = categoryRepository.save(category);

        return category;
    }

    @Override
    public Category updateCategory(Long categoryId, CategoryDto categoryDto) {
        var category = categoryMapper.toEntity(categoryDto);

        category = categoryRepository.save(category);

        return category;
    }

    @Override
    public Boolean deleteCategory(Long categoryId) {
        try {
            categoryRepository.deleteById(categoryId);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
