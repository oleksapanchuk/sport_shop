package dev.oleksa.sportshop.controller;

import dev.oleksa.sportshop.dto.BrandDto;
import dev.oleksa.sportshop.model.product.Brand;
import dev.oleksa.sportshop.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/brands-list")
    public ResponseEntity<List<Brand>> findAllBrand() {
        return ResponseEntity.ok().body(brandService.findBrands());
    }

    @GetMapping("/brands")
    public ResponseEntity<Page<Brand>> findAllBrand(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(name = "sort-field", defaultValue = "") String sortByField,
            @RequestParam(name = "order-is-desc", defaultValue = "desc") Boolean isDesc
    ) {
        return ResponseEntity.ok().body(brandService.findAllBrands(page, size, sortByField, isDesc));
    }

    @GetMapping("/brands/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {

        return ResponseEntity.ok().body(brandService.findBrand(id));
    }

    @PostMapping("/brands")
    public ResponseEntity<Brand> createBrand(@RequestBody BrandDto brandDto) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/brands").toUriString());

        return ResponseEntity.created(uri).body(brandService.createBrand(brandDto));
    }

    @PutMapping("/brands/{brandId}")
    public ResponseEntity<Brand> updateBrand(@RequestBody BrandDto brandDto, @PathVariable Long brandId) {

        return ResponseEntity.accepted().body(brandService.updateBrand(brandDto, brandId));
    }

    @DeleteMapping("/brands/{brandId}")
    public ResponseEntity<Boolean> deleteBrand(@PathVariable Long brandId) {

        return ResponseEntity.ok().body(brandService.deleteBrand(brandId));
    }
}
