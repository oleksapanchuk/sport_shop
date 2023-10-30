package dev.oleksa.sportshop.controller;

import dev.oleksa.sportshop.model.CustomResponse;
import dev.oleksa.sportshop.service.ProductAttributeService;
import dev.oleksa.sportshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Map;

import static dev.oleksa.sportshop.constants.ResponseConstant.GET;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductAttributeService productAttributeService;


//    @RequestParam MultiValueMap<String, String> filters

    @GetMapping("/products")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_CUSTOMER')")
    public ResponseEntity<CustomResponse> findAllProduct(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .path(ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path(String.format("/api/v1/products/page=%d&size=%d", page, size))
                                .toUriString()
                        )
                        .requestMethod(GET)
                        .status(OK)
                        .statusCode(OK.value())
                        .message("Page № " + page + " size = " + size)
                        .data(Map.of("products", productService.getProducts(page, size, null, null)))
                        .build()
        );
    }

    @GetMapping("/product/colors")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<CustomResponse> getAllColors() {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(GET)
                        .statusCode(OK.value())
                        .message("All colors")
                        .data(Map.of("colors", productAttributeService.getProductColors()))
                        .build()
        );
    }

    @GetMapping("/product/color/{colorId}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<CustomResponse> getColorById(
            @PathVariable Long colorId
    ) {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(GET)
                        .statusCode(OK.value())
                        .message("Color id: " + colorId)
                        .data(Map.of("color", productAttributeService.getProductColor(colorId)))
                        .build()
        );
    }

    @GetMapping("/product/size-types")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<CustomResponse> getSizeTypes() {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(GET)
                        .statusCode(OK.value())
                        .message("All size types")
                        .data(Map.of("size-types", productAttributeService.getProductSizeTypes()))
                        .build()
        );
    }

    @GetMapping("/product/size-type/{sizeTypeId}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<CustomResponse> getSizeTypeIdById(
            @PathVariable Long sizeTypeId
    ) {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(GET)
                        .statusCode(OK.value())
                        .message("Size Type id: " + sizeTypeId)
                        .data(Map.of("size-types", productAttributeService.getProductSizeType(sizeTypeId)))
                        .build()
        );
    }

    @GetMapping("/product/all-sizes")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<CustomResponse> getAllSizes() {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(GET)
                        .statusCode(OK.value())
                        .message("All sizes")
                        .data(Map.of("sizes", productAttributeService.getAllProductSizes()))
                        .build()
        );
    }

    @GetMapping("/product/sizes/{sizeTypeId}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<CustomResponse> getAllSizes(
            @PathVariable Long sizeTypeId
    ) {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(GET)
                        .statusCode(OK.value())
                        .message("All sizes by type id: " + sizeTypeId)
                        .data(Map.of("sizes", productAttributeService.getProductSizesByTypeId(sizeTypeId)))
                        .build()
        );
    }

    @GetMapping("/product/size/{sizeId}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<CustomResponse> getSizeById(
            @PathVariable Long sizeId
    ) {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(GET)
                        .statusCode(OK.value())
                        .message("Size Type id: " + sizeId)
                        .data(Map.of("size-types", productAttributeService.getProductSize(sizeId)))
                        .build()
        );
    }
}
