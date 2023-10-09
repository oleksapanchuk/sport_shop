package dev.oleksa.sportshop.mapper;

import dev.oleksa.sportshop.dto.OrderStatusDto;
import dev.oleksa.sportshop.model.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderStatusMapper {

    private final ModelMapper mapper;

    public OrderStatus toEntity(OrderStatusDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, OrderStatus.class);
    }

    public OrderStatusDto toDto(OrderStatus entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, OrderStatusDto.class);
    }

}
