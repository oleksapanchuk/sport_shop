package dev.oleksa.sportshop.mapper;

import dev.oleksa.sportshop.exception.NotFoundException;
import dev.oleksa.sportshop.dto.payment.PaymentDto;
import dev.oleksa.sportshop.model.payment.PaymentMethod;
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
public class PaymentMapper {

    private final ModelMapper mapper;
    private final UserRepository userRepository;

    public PaymentMethod toEntity(PaymentDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, PaymentMethod.class);
    }

    public PaymentDto toDto(PaymentMethod entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, PaymentDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(PaymentMethod.class, PaymentDto.class)
                .addMappings(m -> m.skip(PaymentDto::setUserId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.map(PaymentMethod::getId, PaymentDto::setPaymentId))
        ;
        mapper.createTypeMap(PaymentDto.class, PaymentMethod.class)
                .addMappings(m -> m.skip(PaymentMethod::setUser)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.map(PaymentDto::getPaymentId, PaymentMethod::setId))
        ;
    }

    public Converter<PaymentMethod, PaymentDto> toDtoConverter() {
        return context -> {
            PaymentMethod source = context.getSource();
            PaymentDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public Converter<PaymentDto, PaymentMethod> toEntityConverter() {
        return context -> {
            PaymentDto source = context.getSource();
            PaymentMethod destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public void mapSpecificFields(PaymentDto source, PaymentMethod destination) {
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

    public void mapSpecificFields(PaymentMethod source, PaymentDto destination) {
        destination.setUserId(
                Objects.isNull(source) || Objects.isNull(source.getId())
                        ? null
                        : source.getUser().getId()
        );
    }

}