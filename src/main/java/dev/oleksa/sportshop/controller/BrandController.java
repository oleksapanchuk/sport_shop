package dev.oleksa.sportshop.controller;

import dev.oleksa.sportshop.dto.BrandDto;
import dev.oleksa.sportshop.model.CustomResponse;
import dev.oleksa.sportshop.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

import static dev.oleksa.sportshop.constants.ResponseConstant.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/brands-list")
    public ResponseEntity<CustomResponse> findAllBrand() {
        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .path(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/brands-list").toUriString())
                        .requestMethod(GET)
                        .status(OK)
                        .statusCode(OK.value())
                        .message("All Brands List")
                        .data(Map.of("brands", brandService.findBrands()))
                        .build()
        );
    }

    @GetMapping("/brands")
    public ResponseEntity<CustomResponse> findAllBrand(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(name = "sort-field", defaultValue = "") String sortByField,
            @RequestParam(name = "order-is-desc", defaultValue = "desc") Boolean isDesc
    ) {
        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .path(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/brands").toUriString())
                        .requestMethod(GET)
                        .statusCode(OK.value())
                        .message("Brands page: " + page + " size: " + size + " sort field: " + sortByField + "sort order is desc: " + isDesc)
                        .data(Map.of("brands", brandService.findAllBrands(page, size, sortByField, isDesc)))
                        .build()
        );
    }

    @GetMapping("/brands/{id}")
    public ResponseEntity<CustomResponse> getBrandById(@PathVariable Long id) {
        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .path(ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path(String.format("/api/v1/brands/%d", id))
                                .toUriString()
                        )
                        .requestMethod(GET)
                        .statusCode(OK.value())
                        .message("Brand with ID " + id)
                        .data(Map.of("brand", brandService.findBrand(id)))
                        .build()
        );
    }

    @PostMapping("/brands")
    public ResponseEntity<CustomResponse> createBrand(@RequestBody BrandDto brandDto) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/brands").toUriString());

        return ResponseEntity.created(uri).body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .path(uri.getPath())
                        .requestMethod(POST)
                        .statusCode(CREATED.value())
                        .message("Brand created")
                        .data(Map.of("brand", brandService.createBrand(brandDto)))
                        .build()
        );
    }

    @PutMapping("/brands/{brandId}")
    public ResponseEntity<CustomResponse> updateBrand(@RequestBody BrandDto brandDto, @PathVariable Long brandId) {
        return ResponseEntity.accepted().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .path(ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("/api/v1/brands/" + brandId)
                                .toUriString()
                        )
                        .requestMethod(PUT)
                        .statusCode(ACCEPTED.value())
                        .message("Brand updated")
                        .data(Map.of("brand", brandService.updateBrand(brandDto, brandId)))
                        .build()
        );
    }

    @DeleteMapping("/brands/{brandId}")
    public ResponseEntity<CustomResponse> deleteBrand(@PathVariable Long brandId) {

        if (!brandService.deleteBrand(brandId)) {
            return ResponseEntity.ok().body(
                    CustomResponse.builder()
                            .timeStamp(LocalDateTime.now().toString())
                            .path(ServletUriComponentsBuilder.fromCurrentContextPath()
                                    .path("/api/v1/brands/" + brandId)
                                    .toUriString()
                            )
                            .requestMethod(DELETE)
                            .statusCode(OK.value())
                            .errorMessage("Error")
                            .build()
            );
        }
        return ResponseEntity.accepted().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .path(ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("/api/v1/brands/" + brandId)
                                .toUriString()
                        )
                        .requestMethod(DELETE)
                        .statusCode(ACCEPTED.value())
                        .message("Brand deleted")
                        .build()
        );
    }
}
