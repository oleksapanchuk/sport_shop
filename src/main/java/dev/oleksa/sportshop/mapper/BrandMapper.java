package dev.oleksa.sportshop.mapper;

import dev.oleksa.sportshop.model.dto.BrandDto;
import dev.oleksa.sportshop.model.product.Brand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class BrandMapper {

    private final ModelMapper mapper;

    public Brand toEntity(BrandDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Brand.class);
    }

    public BrandDto toDto(Brand entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, BrandDto.class);
    }
}
