package dev.oleksa.sportshop.mapper;

import dev.oleksa.sportshop.dto.RegionDto;
import dev.oleksa.sportshop.model.user.address.Region;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegionMapper {

    private final ModelMapper mapper;

    public Region toEntity(RegionDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Region.class);
    }

    public RegionDto toDto(Region entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, RegionDto.class);
    }

}
