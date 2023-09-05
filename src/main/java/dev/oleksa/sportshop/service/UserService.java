package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.model.dto.UserCreationDto;
import dev.oleksa.sportshop.model.user.Role;
import dev.oleksa.sportshop.model.user.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity saveUser(UserCreationDto user);
    Role addRoleToUser(Role role);
    UserEntity getUser(String email);
    List<UserEntity> getUsers();
}
