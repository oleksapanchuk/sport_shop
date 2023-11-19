package dev.oleksa.sportshop.controller;

import dev.oleksa.sportshop.dto.UserDto;
import dev.oleksa.sportshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {

        return ResponseEntity.ok().body(userService.getUserById(userId));
    }

    @PatchMapping("/user/{userId}/info")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<Boolean> updateUserInfo(
            @PathVariable Long userId,
            @RequestBody UserDto userDto
    ) {
        return ResponseEntity.accepted().body(userService.updateUser(userId, userDto));
    }

    @PatchMapping("/user/{userId}/avatar")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<Boolean> updateUserAvatar(
            @PathVariable Long userId,
            @RequestParam String imageUrl
    ) {
        return ResponseEntity.accepted().body(userService.updateAvatar(userId, imageUrl));
    }

    @DeleteMapping("/user/{userId}/avatar")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<Boolean> deleteUserAvatar(
            @PathVariable Long userId
    ) {
        return ResponseEntity.accepted().body(userService.deleteAvatar(userId));
    }

    @PatchMapping("/user/{userId}/gender/{genderId}")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<Boolean> updateUserGender(
            @PathVariable Long userId,
            @PathVariable Long genderId
    ) {
        return ResponseEntity.accepted().body(userService.updateGender(userId, genderId));
    }


}
