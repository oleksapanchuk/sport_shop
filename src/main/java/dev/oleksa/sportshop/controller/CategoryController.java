package dev.oleksa.sportshop.controller;

import dev.oleksa.sportshop.model.CustomResponse;
import dev.oleksa.sportshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

import static dev.oleksa.sportshop.constants.ResponseConstant.GET;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/product/all-categories")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<CustomResponse> getAllCategories() {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(GET)
                        .statusCode(OK.value())
                        .message("All categories")
                        .data(Map.of("categories", categoryService.getAllCategories()))
                        .build()
        );
    }

    @GetMapping("/product/categories/{parentCategoryId}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<CustomResponse> getCategoriesByParentId(
            @PathVariable Long parentCategoryId
    ) {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(GET)
                        .statusCode(OK.value())
                        .message("All categories by parent category id: " + parentCategoryId)
                        .data(Map.of("categories", categoryService.getCategoriesByParentId(parentCategoryId)))
                        .build()
        );
    }

    @GetMapping("/product/category/{categoryId}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<CustomResponse> getCategoryById(
            @PathVariable Long categoryId
    ) {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(GET)
                        .statusCode(OK.value())
                        .message("Category id: " + categoryId)
                        .data(Map.of("category", categoryService.getCategoryById(categoryId)))
                        .build()
        );
    }

}
