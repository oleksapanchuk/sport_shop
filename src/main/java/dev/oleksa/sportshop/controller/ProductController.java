package dev.oleksa.sportshop.controller;

import dev.oleksa.sportshop.dto.ProductDto;
import dev.oleksa.sportshop.dto.response.ProductResponse;
import dev.oleksa.sportshop.model.product.Product;
import dev.oleksa.sportshop.model.product.ProductColor;
import dev.oleksa.sportshop.model.product.ProductSize;
import dev.oleksa.sportshop.model.product.ProductSizeType;
import dev.oleksa.sportshop.service.ProductAttributeService;
import dev.oleksa.sportshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductAttributeService productAttributeService;


//    @RequestParam MultiValueMap<String, String> filters

    @GetMapping("/products")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_CUSTOMER')")
    public ResponseEntity<Page<Product>> findAllProduct(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return ResponseEntity.ok().body(productService.getProducts(page, size, null, null));
    }

    @GetMapping("/products/{productId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_CUSTOMER')")
    public ResponseEntity<ProductDto> findProductById(
            @PathVariable Long productId
    ) {
        return ResponseEntity.ok().body(productService.readProduct(productId));
    }

    @GetMapping("/products/details/{productId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_CUSTOMER')")
    public ResponseEntity<ProductResponse> getProductDetails(
            @PathVariable Long productId
    ) {
        return ResponseEntity.ok().body(productService.getProductDetailsById(productId, "ua"));
    }

    @GetMapping("/product/colors")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<List<ProductColor>> getAllColors() {

        return ResponseEntity.ok().body(productAttributeService.getProductColors());
    }

    @GetMapping("/product/color/{colorId}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<ProductColor> getColorById(
            @PathVariable Long colorId
    ) {

        return ResponseEntity.ok().body(productAttributeService.getProductColor(colorId));
    }

    @GetMapping("/product/size-types")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<List<ProductSizeType>> getSizeTypes() {

        return ResponseEntity.ok().body(productAttributeService.getProductSizeTypes());
    }

    @GetMapping("/product/size-type/{sizeTypeId}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<ProductSizeType> getSizeTypeIdById(
            @PathVariable Long sizeTypeId
    ) {

        return ResponseEntity.ok().body(productAttributeService.getProductSizeType(sizeTypeId));
    }

    @GetMapping("/product/all-sizes")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<List<ProductSize>> getAllSizes() {

        return ResponseEntity.ok().body(productAttributeService.getAllProductSizes());
    }

    @GetMapping("/product/sizes/{sizeTypeId}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<List<ProductSize>> getAllSizes(
            @PathVariable Long sizeTypeId
    ) {

        return ResponseEntity.ok().body(productAttributeService.getProductSizesByTypeId(sizeTypeId));
    }

    @GetMapping("/product/size/{sizeId}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<ProductSize> getSizeById(
            @PathVariable Long sizeId
    ) {

        return ResponseEntity.ok().body(productAttributeService.getProductSize(sizeId));
    }
}
