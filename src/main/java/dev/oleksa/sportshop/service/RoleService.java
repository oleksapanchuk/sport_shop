package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.model.dto.RoleDto;

public interface RoleService {
    RoleDto createRole(RoleDto roleDto);

    RoleDto readRole(Long roleId);

    RoleDto updateRole(RoleDto roleDto);

    Boolean deleteRole(Long roleId);
}
