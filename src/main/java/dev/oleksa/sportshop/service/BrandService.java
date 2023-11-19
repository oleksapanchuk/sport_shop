package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.dto.BrandDto;
import dev.oleksa.sportshop.model.product.Brand;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BrandService {
    List<Brand> findBrands();

    Page<Brand> findAllBrands(int size, int page, String sortByField, Boolean isDesc);

    Brand findBrand(Long id);

    Brand createBrand(BrandDto brandData);

    Brand updateBrand(BrandDto brandData, Long brandId);

    Boolean deleteBrand(Long brandId);
}
