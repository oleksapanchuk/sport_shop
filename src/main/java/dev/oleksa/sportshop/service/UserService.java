package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.dto.UserDto;
import dev.oleksa.sportshop.model.user.Role;
import dev.oleksa.sportshop.model.user.UserEntity;
import dev.oleksa.sportshop.model.user.address.Address;

import java.util.List;

public interface UserService {
    UserDto getUserById(Long id);
    UserEntity getUserByEmail(String email);

    UserEntity createUser(UserDto user);

    UserEntity updateUser(UserEntity user);

    UserEntity addRoleToUser(UserEntity user, Role role);

    UserEntity removeRoleFromUser(UserEntity user, Role role);
    UserEntity addAvatar(UserEntity user, String filePath);
    UserEntity removeAvatar(UserEntity user);
    UserEntity addAddressToUser(UserEntity user, Address address);
    UserEntity removeAddress(UserEntity user, Address address);

    List<UserEntity> getUsers();
}
