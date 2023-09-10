package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.model.dto.SizeDto;

public interface SizeService {
    SizeDto createSize(SizeDto sizeDto);

    SizeDto readSize(Long sizeId);

    SizeDto updateSize(SizeDto sizeDto);

    Boolean deleteSize(Long sizeId);
}
