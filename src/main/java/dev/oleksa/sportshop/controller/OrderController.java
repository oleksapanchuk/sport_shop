package dev.oleksa.sportshop.controller;

import dev.oleksa.sportshop.model.order.OrderStatus;
import dev.oleksa.sportshop.model.order.ShippingMethod;
import dev.oleksa.sportshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping("/order/statuses")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<List<OrderStatus>> getOrderStatuses() {

        return ResponseEntity.ok().body(orderService.getOrderStatuses());
    }

    @GetMapping("/order/status/{orderStatusId}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<OrderStatus> getOrderStatus(
            @PathVariable Long orderStatusId
    ) {

        return ResponseEntity.ok().body(orderService.getOrderStatus(orderStatusId));
    }

    @GetMapping("/order/shipping-methods")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<List<ShippingMethod>> getShippingMethods() {

        return ResponseEntity.ok().body(orderService.getShippingMethods());
    }

    @GetMapping("/order/shipping-method/{shippingMethodId}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<ShippingMethod> getShippingMethod(
            @PathVariable Long shippingMethodId
    ) {

        return ResponseEntity.ok().body(orderService.getShippingMethod(shippingMethodId));
    }

}
