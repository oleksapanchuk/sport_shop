package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.dto.RegionDto;
import dev.oleksa.sportshop.mapper.RegionMapper;
import dev.oleksa.sportshop.model.user.address.Region;
import dev.oleksa.sportshop.repository.RegionRepository;
import dev.oleksa.sportshop.service.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @Override
    public Region createRegion(RegionDto regionDto) {
        var region = regionMapper.toEntity(regionDto);
        return regionRepository.save(region);
    }

    @Override
    public Region getRegionById(Long regionId) {
        return regionRepository.findById(regionId)
                .orElseThrow();
    }

    @Override
    public Region updateRegion(Long regionId, RegionDto regionDto) {
        var region = regionMapper.toEntity(regionDto);
        region.setId(regionId);

        return regionRepository.save(region);
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
