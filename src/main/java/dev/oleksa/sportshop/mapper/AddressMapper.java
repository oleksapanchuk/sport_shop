package dev.oleksa.sportshop.mapper;

import dev.oleksa.sportshop.exception.NotFoundException;
import dev.oleksa.sportshop.dto.request.AddressRequest;
import dev.oleksa.sportshop.model.user.address.Address;
import dev.oleksa.sportshop.repository.RegionRepository;
import dev.oleksa.sportshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class AddressMapper {

    private final ModelMapper mapper;
    private final RegionRepository regionRepository;
    private final UserRepository userRepository;

    public Optional<Address> toEntity(AddressRequest addressRequest) {
        return Objects.isNull(addressRequest) ? Optional.empty() : Optional.of(mapper.map(addressRequest, Address.class));
    }

//    public AddressRequest toDto(Address entity) {
//        return Objects.isNull(entity) ? null : mapper.map(entity, AddressRequest.class);
//    }

    @PostConstruct
    public void setupMapper() {
//        mapper.createTypeMap(Address.class, AddressRequest.class)
//                .addMappings(m -> m.skip(AddressRequest::setUserId)).setPostConverter(toDtoConverter())
//                .addMappings(m -> m.skip(AddressRequest::setRegionId)).setPostConverter(toDtoConverter())
//        ;
        mapper.createTypeMap(AddressRequest.class, Address.class)
                .addMappings(m -> m.skip(Address::setUser)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Address::setRegion)).setPostConverter(toEntityConverter())
        ;
    }

//    public Converter<Address, AddressRequest> toDtoConverter() {
//        return context -> {
//            Address source = context.getSource();
//            AddressRequest destination = context.getDestination();
//            mapSpecificFields(source, destination);
//            return context.getDestination();
//        };
//    }

    public Converter<AddressRequest, Address> toEntityConverter() {
        return context -> {
            AddressRequest source = context.getSource();
            Address destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public void mapSpecificFields(AddressRequest source, Address destination) {
        try {
            destination.setUser(
                    Objects.isNull(source) || Objects.isNull(source.getRegionId())
                            ? null
                            : userRepository.findById(source.getUserId())
                            .orElseThrow(() -> new NotFoundException("User not found"))
            );
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

//    public void mapSpecificFields(Address source, AddressRequest destination) {
//        destination.setUserId(
//                Objects.isNull(source) || Objects.isNull(source.getUser().getId())
//                        ? null
//                        : source.getUser().getId()
//        );
//        destination.setRegionId(
//                Objects.isNull(source) || Objects.isNull(source.getRegion().getId())
//                        ? null
//                        : source.getRegion().getId()
//        );
//    }
}
