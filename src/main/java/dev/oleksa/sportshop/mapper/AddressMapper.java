package dev.oleksa.sportshop.mapper;

import dev.oleksa.sportshop.exception.NotFoundException;
import dev.oleksa.sportshop.dto.AddressDto;
import dev.oleksa.sportshop.model.user.address.Address;
import dev.oleksa.sportshop.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class AddressMapper {

    private final ModelMapper mapper;
    private final RegionRepository regionRepository;

    public Address toEntity(AddressDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Address.class);
    }

    public AddressDto toDto(Address entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, AddressDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Address.class, AddressDto.class)
                .addMappings(m -> m.skip(AddressDto::setRegionId)).setPostConverter(toDtoConverter())
        ;
        mapper.createTypeMap(AddressDto.class, Address.class)
                .addMappings(m -> m.skip(Address::setRegion)).setPostConverter(toEntityConverter())
        ;
    }

    public Converter<Address, AddressDto> toDtoConverter() {
        return context -> {
            Address source = context.getSource();
            AddressDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public Converter<AddressDto, Address> toEntityConverter() {
        return context -> {
            AddressDto source = context.getSource();
            Address destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public void mapSpecificFields(AddressDto source, Address destination) {
        try {
            destination.setRegion(
                    Objects.isNull(source) || Objects.isNull(source.getRegionId())
                            ? null
                            : regionRepository.findById(source.getRegionId())
                            .orElseThrow(() -> new NotFoundException("Region not found"))
            );
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void mapSpecificFields(Address source, AddressDto destination) {
        destination.setRegionId(
                Objects.isNull(source) || Objects.isNull(source.getId())
                        ? null
                        : source.getRegion().getId()
        );
    }
}
