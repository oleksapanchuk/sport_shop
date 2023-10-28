package dev.oleksa.sportshop.controller;

import dev.oleksa.sportshop.dto.UserDto;
import dev.oleksa.sportshop.model.CustomResponse;
import dev.oleksa.sportshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

import static dev.oleksa.sportshop.constants.ResponseConstant.DELETE;
import static dev.oleksa.sportshop.constants.ResponseConstant.PATCH;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/user/{userId}")
    public ResponseEntity<CustomResponse> getUserById(@PathVariable Long userId) {

        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .message("User found")
                        .requestMethod("GET")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(Map.of("user", service.getUserById(userId)))
                        .build()
        );
    }

    @PatchMapping("/user/{userId}/info")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<CustomResponse> updateUserInfo(
            @PathVariable Long userId,
            @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(PATCH)
                        .statusCode(OK.value())
                        .message("User info updated. User id: " + userId)
                        .data(Map.of("is_changed", service.updateUser(userId, userDto)))
                        .build()
        );
    }

    @PatchMapping("/user/{userId}/avatar")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<CustomResponse> updateUserAvatar(
            @PathVariable Long userId,
            @RequestParam String imageUrl
    ) {
        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(PATCH)
                        .statusCode(OK.value())
                        .message("Updated user avatar. User id: " + userId)
                        .data(Map.of("is_changed", service.updateAvatar(userId, imageUrl)))
                        .build()
        );
    }

    @DeleteMapping("/user/{userId}/avatar")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<CustomResponse> deleteUserAvatar(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(DELETE)
                        .statusCode(OK.value())
                        .message("Updated user avatar. User id: " + userId)
                        .data(Map.of("is_deleted", service.deleteAvatar(userId)))
                        .build()
        );
    }

    @PatchMapping("/user/{userId}/gender/{genderId}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<CustomResponse> updateUserGender(
            @PathVariable Long userId,
            @PathVariable Long genderId
    ) {
        return ResponseEntity.ok().body(
                CustomResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .requestMethod(PATCH)
                        .statusCode(OK.value())
                        .message("Updated user gender. User id: " + userId + " Gender id: " + genderId)
                        .data(Map.of("is_changed", service.updateGender(userId, genderId)))
                        .build()
        );
    }


}
