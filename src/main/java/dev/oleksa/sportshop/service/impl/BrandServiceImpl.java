package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.mapper.BrandMapper;
import dev.oleksa.sportshop.dto.BrandDto;
import dev.oleksa.sportshop.model.product.Brand;
import dev.oleksa.sportshop.repository.BrandRepository;
import dev.oleksa.sportshop.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    public List<Brand> findBrands() {
        List<Brand> brands = new ArrayList<>();
        for (Brand brand : brandRepository.findAll()) {
            brands.add(brand);
        }
        return brands;
    }

    @Override
    public Page<Brand> findAllBrands(int page, int size, String sortByField, Boolean isDesc) {
        Sort sort = isDesc ? Sort.by(sortByField).descending() : Sort.by(sortByField);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return brandRepository.findAll(pageRequest);
    }

    @Override
    public Optional<Brand> findBrand(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    public Brand createBrand(BrandDto brandDto) {
        Brand brand = brandMapper.toEntity(brandDto);
        return brandRepository.save(brand);
    }

    @Override
    public Brand updateBrand(BrandDto brandDto, Long brandId) {
        Brand brand = Brand.builder()
                .id(brandId)
                .name(brandDto.getName())
                .officialSiteUrl(brandDto.getOfficialSiteUrl())
                .logoImageUrl(brandDto.getLogoImageUrl())
                .build();
        return brandRepository.save(brand);
    }

    @Override
    public Boolean deleteBrand(Long brandId) {
        try {
            brandRepository.deleteById(brandId);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

}
