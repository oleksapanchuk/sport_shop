package dev.oleksa.sportshop.controller;

import dev.oleksa.sportshop.dto.BrandDto;
import dev.oleksa.sportshop.model.CustomResponse;
import dev.oleksa.sportshop.service.BrandService;
import dev.oleksa.sportshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

import static dev.oleksa.sportshop.constants.ResponseConstant.GET;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


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


}
