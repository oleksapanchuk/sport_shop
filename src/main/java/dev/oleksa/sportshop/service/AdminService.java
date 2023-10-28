package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.dto.UserDto;
import dev.oleksa.sportshop.security.auth.RegisterRequest;

public interface AdminService {
    UserDto  createUser(RegisterRequest request);
    Boolean addRoleToUser(Long userId, Long roleId);
    Boolean removeRoleFromUser(Long userId, Long roleId);
}
