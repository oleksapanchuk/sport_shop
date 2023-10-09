package dev.oleksa.sportshop.controller;

import dev.oleksa.sportshop.dto.UserDto;
import dev.oleksa.sportshop.model.user.UserEntity;
import dev.oleksa.sportshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/sport-shop")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/user/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        return service.getUserById(userId);
    }

    @PostMapping("/user/save")
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserDto user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/sport-shop/user/save").toUriString());
        return ResponseEntity.created(uri).body(service.createUser(user));
    }

}
