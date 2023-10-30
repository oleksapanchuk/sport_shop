package dev.oleksa.sportshop.controller;

import dev.oleksa.sportshop.model.CustomResponse;
import dev.oleksa.sportshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

import static dev.oleksa.sportshop.constants.ResponseConstant.GET;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping("/order/statuses")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<CustomResponse> getOrderStatuses() {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(GET)
                        .statusCode(OK.value())
                        .message("All order statuses")
                        .data(Map.of("order-statuses", orderService.getOrderStatuses()))
                        .build()
        );
    }

    @GetMapping("/order/status/{orderStatusId}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<CustomResponse> getOrderStatus(
            @PathVariable Long orderStatusId
    ) {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(GET)
                        .statusCode(OK.value())
                        .message("Order status id: " + orderStatusId)
                        .data(Map.of("order", orderService.getOrderStatus(orderStatusId)))
                        .build()
        );
    }

    @GetMapping("/order/shipping-methods")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<CustomResponse> getShippingMethods() {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(GET)
                        .statusCode(OK.value())
                        .message("All order shipping methods")
                        .data(Map.of("shipping-methods", orderService.getShippingMethods()))
                        .build()
        );
    }

    @GetMapping("/order/shipping-method/{shippingMethodId}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<CustomResponse> getShippingMethod(
            @PathVariable Long shippingMethodId
    ) {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(GET)
                        .statusCode(OK.value())
                        .message("Shipping Method id: " + shippingMethodId)
                        .data(Map.of("order", orderService.getShippingMethod(shippingMethodId)))
                        .build()
        );
    }

}
