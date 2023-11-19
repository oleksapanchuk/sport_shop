package dev.oleksa.sportshop.controller;

import dev.oleksa.sportshop.model.product.Category;
import dev.oleksa.sportshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/product/all-categories")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<List<Category>> getAllCategories() {

        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }

    @GetMapping("/product/categories/{parentCategoryId}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<List<Category>> getCategoriesByParentId(
            @PathVariable Long parentCategoryId
    ) {

        return ResponseEntity.ok().body(categoryService.getCategoriesByParentId(parentCategoryId));
    }

    @GetMapping("/product/category/{categoryId}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<Category> getCategoryById(
            @PathVariable Long categoryId
    ) {

        return ResponseEntity.ok().body(categoryService.getCategoryById(categoryId));
    }

}
