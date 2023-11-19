package dev.oleksa.sportshop.controller;

import dev.oleksa.sportshop.dto.request.AddressRequest;
import dev.oleksa.sportshop.exception.NotFoundException;
import dev.oleksa.sportshop.model.user.address.Address;
import dev.oleksa.sportshop.model.user.address.Region;
import dev.oleksa.sportshop.service.AddressService;
import dev.oleksa.sportshop.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final RegionService regionService;

    @GetMapping("/user/{userId}/address")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<List<Address>> getAddressForUser(
            @PathVariable Long userId
    ) throws NotFoundException {

        return ResponseEntity.ok().body(addressService.readAddressesForUser(userId));
    }

    @GetMapping("/user/{userId}/address/{addressId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<Address> getAddress(
            @PathVariable Long userId,
            @PathVariable Long addressId
    ) throws NotFoundException {

        return ResponseEntity.ok().body(addressService.readAddress(addressId));
    }

    @PostMapping("/user/{userId}/address")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<Address> addAddress(
            @PathVariable Long userId,
            @RequestBody AddressRequest addressRequest
    ) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(String.format("/api/user/{%d}/addresses", userId)).toUriString());

        return ResponseEntity.created(uri).body(addressService.createAddress(addressRequest));
    }

    @PutMapping("/user/{userId}/address/{addressId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<Address> updateAddress(
            @PathVariable Long userId,
            @PathVariable Long addressId,
            @RequestBody AddressRequest addressRequest
    ) {

        return ResponseEntity.ok().body(addressService.updateAddress(addressId, addressRequest));
    }

    @DeleteMapping("/user/{userId}/address/{addressId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<Boolean> deleteAddress(
            @PathVariable Long userId,
            @PathVariable Long addressId
    ) {

        return ResponseEntity.ok().body(addressService.deleteAddress(addressId));
    }

    @GetMapping("/address/regions")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<List<Region>> getAllRegions() {

        return ResponseEntity.ok().body(regionService.getAllRegions());
    }

    @GetMapping("/address/region/{regionId}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<Region> getRegionById(
            @PathVariable Long regionId
    ) {
        return ResponseEntity.ok().body(regionService.getRegionById(regionId));
    }

}
