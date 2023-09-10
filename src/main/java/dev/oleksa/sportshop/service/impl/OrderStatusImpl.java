package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.mapper.OrderStatusMapper;
import dev.oleksa.sportshop.model.dto.OrderStatusDto;
import dev.oleksa.sportshop.repository.OrderStatusRepository;
import dev.oleksa.sportshop.service.OrderStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderStatusImpl implements OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;
    private final OrderStatusMapper orderStatusMapper;

    @Override
    public OrderStatusDto createOrderStatus(OrderStatusDto orderStatusDto) {
        var orderStatus = orderStatusMapper.toEntity(orderStatusDto);

        orderStatus = orderStatusRepository.save(orderStatus);

        return orderStatusMapper.toDto(orderStatus);
    }

    @Override
    public OrderStatusDto readOrderStatus(Long orderStatusId) {
        return orderStatusMapper.toDto(
                orderStatusRepository.findById(orderStatusId)
                        .orElseThrow()
        );
    }

    @Override
    public OrderStatusDto updateOrderStatus(OrderStatusDto orderStatusDto) {
        var orderStatus = orderStatusMapper.toEntity(orderStatusDto);

        orderStatus = orderStatusRepository.save(orderStatus);

        return orderStatusMapper.toDto(orderStatus);
    }

    @Override
    public Boolean deleteOrderStatus(Long orderStatusId) {
        try {
            orderStatusRepository.deleteById(orderStatusId);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
