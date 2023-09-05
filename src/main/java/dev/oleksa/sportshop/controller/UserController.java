package dev.oleksa.sportshop.controller;

import dev.oleksa.sportshop.model.dto.UserCreationDto;
import dev.oleksa.sportshop.model.user.UserEntity;
import dev.oleksa.sportshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/sport-shop")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/user/save")
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserCreationDto user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/sport-shop/user/save").toUriString());
        return ResponseEntity.created(uri).body(service.saveUser(user));
    }

}
