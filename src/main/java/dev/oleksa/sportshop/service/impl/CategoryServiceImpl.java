package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.mapper.CategoryMapper;
import dev.oleksa.sportshop.model.dto.CategoryDto;
import dev.oleksa.sportshop.repository.CategoryRepository;
import dev.oleksa.sportshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        var category = categoryMapper.toEntity(categoryDto);

        category = categoryRepository.save(category);

        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto readCategory(Long categoryId) {
        return categoryMapper.toDto(
                categoryRepository.findById(categoryId)
                        .orElseThrow()
        );
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        var category = categoryMapper.toEntity(categoryDto);

        category = categoryRepository.save(category);

        return categoryMapper.toDto(category);
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
