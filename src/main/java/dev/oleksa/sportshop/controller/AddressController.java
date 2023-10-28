package dev.oleksa.sportshop.controller;

import dev.oleksa.sportshop.dto.request.AddressRequest;
import dev.oleksa.sportshop.exception.NotFoundException;
import dev.oleksa.sportshop.model.CustomResponse;
import dev.oleksa.sportshop.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

import static dev.oleksa.sportshop.constants.ResponseConstant.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/user/{userId}/address")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<CustomResponse> getAddressForUser(
            @PathVariable Long userId
    ) throws NotFoundException {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(GET)
                        .statusCode(OK.value())
                        .message("All addresses. User id: " + userId)
                        .data(Map.of("addresses", addressService.readAddressesForUser(userId)))
                        .build()
        );
    }

    @GetMapping("/user/{userId}/address/{addressId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<CustomResponse> getAddress(
            @PathVariable Long userId,
            @PathVariable Long addressId
    ) throws NotFoundException {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(GET)
                        .statusCode(OK.value())
                        .message("Address id:" + addressId + " User id: " + userId)
                        .data(Map.of("address", addressService.readAddress(addressId)))
                        .build()
        );
    }

    @PostMapping("/user/{userId}/address")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<CustomResponse> addAddress(
            @PathVariable Long userId,
            @RequestBody AddressRequest addressRequest
    ) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(String.format("/api/user/{%d}/addresses", userId)).toUriString());

        return ResponseEntity.created(uri).body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .path(uri.getPath())
                        .requestMethod(POST)
                        .statusCode(CREATED.value())
                        .message("Address added")
                        .data(Map.of("address", addressService.createAddress(addressRequest)))
                        .build()
        );
    }

    @PutMapping ("/user/{userId}/address/{addressId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<CustomResponse> updateAddress(
            @PathVariable Long userId,
            @PathVariable Long addressId,
            @RequestBody AddressRequest addressRequest
    ) {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(PUT)
                        .statusCode(OK.value())
                        .message("Updated address id:" + addressId + " User id: " + userId)
                        .data(Map.of("address", addressService.updateAddress(addressId, addressRequest)))
                        .build()
        );
    }

    @DeleteMapping ("/user/{userId}/address/{addressId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<CustomResponse> deleteAddress(
            @PathVariable Long userId,
            @PathVariable Long addressId
    ) {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(DELETE)
                        .statusCode(OK.value())
                        .message("Updated address id:"  + addressId + " User id: " + userId)
                        .data(Map.of("address", addressService.deleteAddress(addressId)))
                        .build()
        );
    }
}
