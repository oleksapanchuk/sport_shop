package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.model.dto.RegionDto;

public interface RegionService {
    RegionDto createRegion(RegionDto regionDto);

    RegionDto readRegion(Long regionId);

    RegionDto updateRegion(RegionDto regionDto);

    Boolean deleteRegion(Long regionId);
}
