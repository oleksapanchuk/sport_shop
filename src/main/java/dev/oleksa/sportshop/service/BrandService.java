package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.dto.BrandDto;

public interface BrandService {
    BrandDto createBrand(BrandDto brand);

    BrandDto readBrand(Long brandId);

    BrandDto updateBrand(BrandDto brand);

    Boolean deleteBrand(Long brandId);
}
