package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.mapper.BrandMapper;
import dev.oleksa.sportshop.model.dto.BrandDto;
import dev.oleksa.sportshop.repository.BrandRepository;
import dev.oleksa.sportshop.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    public BrandDto createBrand(BrandDto brandDto) {
        var brand = brandMapper.toEntity(brandDto);

        brand = brandRepository.save(brand);

        return brandMapper.toDto(brand);
    }

    @Override
    public BrandDto readBrand(Long brandId) {
        return brandMapper.toDto(
                brandRepository.findById(brandId)
                        .orElseThrow()
        );
    }

    @Override
    public BrandDto updateBrand(BrandDto brandDto) {
        var brand = brandMapper.toEntity(brandDto);

        brand = brandRepository.save(brand);

        return brandMapper.toDto(brand);
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
