package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.mapper.RegionMapper;
import dev.oleksa.sportshop.dto.RegionDto;
import dev.oleksa.sportshop.repository.RegionRepository;
import dev.oleksa.sportshop.service.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    @Override
    public RegionDto createRegion(RegionDto regionDto) {
        var region = regionMapper.toEntity(regionDto);

        region = regionRepository.save(region);

        return regionMapper.toDto(region);
    }

    @Override
    public RegionDto readRegion(Long regionId) {
        return regionMapper.toDto(
                regionRepository.findById(regionId)
                        .orElseThrow()
        );
    }

    @Override
    public RegionDto updateRegion(RegionDto regionDto) {
        var region = regionMapper.toEntity(regionDto);

        region = regionRepository.save(region);

        return regionMapper.toDto(region);
    }

    @Override
    public Boolean deleteRegion(Long regionId) {
        try {
            regionRepository.deleteById(regionId);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
