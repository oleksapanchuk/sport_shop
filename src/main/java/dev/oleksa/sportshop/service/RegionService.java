package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.dto.RegionDto;
import dev.oleksa.sportshop.model.user.address.Region;

import java.util.List;

public interface RegionService {
    List<Region> getAllRegions();

    Region createRegion(RegionDto regionDto);

    Region getRegionById(Long regionId);

    Region updateRegion(Long regionId, RegionDto regionDto);

    Boolean deleteRegion(Long regionId);
}
