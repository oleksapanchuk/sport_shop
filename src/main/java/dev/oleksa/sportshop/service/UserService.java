package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.dto.UserDto;
import dev.oleksa.sportshop.model.user.UserEntity;

public interface UserService {

    UserEntity getUserByEmail(String email);

    UserDto getUserById(Long id);

    Boolean updateUser(Long userId, UserDto userDto);

    Boolean updateAvatar(Long userId, String imageUrl);

    Boolean deleteAvatar(Long userId);

    Boolean updateGender(Long userId, Long genderId);

    Boolean confirmAccount(Long userId);

    String generateConfirmationToken(String email);

    Boolean verifyConfirmationToken(String token);

    Boolean updatePassword(Long userId, String oldPassword, String newPassword);
}
