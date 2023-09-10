package dev.oleksa.sportshop.mapper;

import dev.oleksa.sportshop.exception.NotFoundException;
import dev.oleksa.sportshop.model.dto.ConfirmationDto;
import dev.oleksa.sportshop.model.user.Confirmation;
import dev.oleksa.sportshop.repository.UserRepository;
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
public class ConfirmationMapper {

    private final ModelMapper mapper;
    private final UserRepository userRepository;

    public Confirmation toEntity(ConfirmationDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Confirmation.class);
    }

    public ConfirmationDto toDto(Confirmation entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, ConfirmationDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Confirmation.class, ConfirmationDto.class)
                .addMappings(m -> m.skip(ConfirmationDto::setUserId)).setPostConverter(toDtoConverter())
        ;
        mapper.createTypeMap(ConfirmationDto.class, Confirmation.class)
                .addMappings(m -> m.skip(Confirmation::setUser)).setPostConverter(toEntityConverter())
        ;
    }

    public Converter<Confirmation, ConfirmationDto> toDtoConverter() {
        return context -> {
            Confirmation source = context.getSource();
            ConfirmationDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public Converter<ConfirmationDto, Confirmation> toEntityConverter() {
        return context -> {
            ConfirmationDto source = context.getSource();
            Confirmation destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public void mapSpecificFields(ConfirmationDto source, Confirmation destination) {
        try {
            destination.setUser(
                    Objects.isNull(source) || Objects.isNull(source.getUserId())
                            ? null
                            : userRepository.findById(source.getUserId())
                            .orElseThrow(() -> new NotFoundException("User not found"))
            );
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void mapSpecificFields(Confirmation source, ConfirmationDto destination) {
        destination.setUserId(
                Objects.isNull(source) || Objects.isNull(source.getId())
                        ? null
                        : source.getUser().getId()
        );
    }

}
