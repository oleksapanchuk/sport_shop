package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.model.dto.OrderStatusDto;

public interface OrderStatusService {
    OrderStatusDto createOrderStatus(OrderStatusDto orderStatusDto);

    OrderStatusDto readOrderStatus(Long orderStatusId);

    OrderStatusDto updateOrderStatus(OrderStatusDto orderStatusDto);

    Boolean deleteOrderStatus(Long orderStatusId);
}
