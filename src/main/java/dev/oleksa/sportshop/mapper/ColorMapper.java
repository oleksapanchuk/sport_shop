package dev.oleksa.sportshop.mapper;

import dev.oleksa.sportshop.dto.ColorDto;
import dev.oleksa.sportshop.model.product.ProductColor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class ColorMapper {

    private final ModelMapper mapper;

    public ProductColor toEntity(ColorDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, ProductColor.class);
    }

    public ColorDto toDto(ProductColor entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, ColorDto.class);
    }

}
