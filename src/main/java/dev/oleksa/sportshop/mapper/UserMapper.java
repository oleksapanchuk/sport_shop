package dev.oleksa.sportshop.mapper;

import dev.oleksa.sportshop.dto.UserDto;
import dev.oleksa.sportshop.model.user.UserEntity;
import dev.oleksa.sportshop.repository.GenderRepository;
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
public class UserMapper {

    private final ModelMapper mapper;
    private final GenderRepository genderRepository;

    public UserEntity toEntity(UserDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, UserEntity.class);
    }

    public UserDto toDto(UserEntity entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, UserDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(UserEntity.class, UserDto.class)
                .addMappings(m -> m.skip(UserDto::setGenderId)).setPostConverter(toDtoConverter())
        ;
        mapper.createTypeMap(UserDto.class, UserEntity.class)
                .addMappings(m -> m.skip(UserEntity::setGender)).setPostConverter(toEntityConverter())
        ;
    }

    public Converter<UserEntity, UserDto> toDtoConverter() {
        return context -> {
            UserEntity source = context.getSource();
            UserDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public Converter<UserDto, UserEntity> toEntityConverter() {
        return context -> {
            UserDto source = context.getSource();
            UserEntity destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public void mapSpecificFields(UserDto source, UserEntity destination) {
        destination.setGender(
                Objects.isNull(source) || Objects.isNull(source.getGenderId())
                        ? null
                        : genderRepository.findById(source.getGenderId()).orElseThrow()
        );
    }

    public void mapSpecificFields(UserEntity source, UserDto destination) {
        destination.setGenderId(
                Objects.isNull(source) || Objects.isNull(source.getGender())
                        ? null
                        : source.getGender().getId()
        );
    }

}
