package dev.oleksa.sportshop.controller;

import dev.oleksa.sportshop.model.dto.BrandDto;
import dev.oleksa.sportshop.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/sport-shop/manager/product")
@RequiredArgsConstructor
public class ProductController {

    private final BrandService brandService;


    @GetMapping("/brand/{brandId}")
    public ResponseEntity<BrandDto> getBrandById(@PathVariable Long brandId) {
        return ResponseEntity.ok(brandService.readBrand(brandId));
    }

    @PostMapping("/brand/save")
    public ResponseEntity<BrandDto> savePayment(@RequestBody BrandDto brandDto) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/sport-shop/manager/product/brand/save").toUriString());
        return ResponseEntity.created(uri).body(brandService.createBrand(brandDto));
    }

    @PutMapping("brand/update")
    public ResponseEntity<BrandDto> updatePayment(@RequestBody BrandDto brandDto) {
        return ResponseEntity.ok(brandService.updateBrand(brandDto));
    }

    @DeleteMapping("brand/delete/{paymentId}")
    public ResponseEntity<String> deletePayment(@PathVariable Long paymentId) {
        if (brandService.deleteBrand(paymentId)) {
            return ResponseEntity.ok("Payment successfully deleted");
        }
        return ResponseEntity.ok("NOT");
    }
}
