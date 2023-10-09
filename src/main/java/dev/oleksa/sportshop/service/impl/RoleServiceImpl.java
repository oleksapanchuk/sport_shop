package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.mapper.RoleMapper;
import dev.oleksa.sportshop.dto.RoleDto;
import dev.oleksa.sportshop.repository.RoleRepository;
import dev.oleksa.sportshop.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        var role = roleMapper.toEntity(roleDto);

        role = roleRepository.save(role);

        return roleMapper.toDto(role);
    }

    @Override
    public RoleDto readRole(Long roleId) {
        return roleMapper.toDto(
                roleRepository.findById(roleId)
                        .orElseThrow()
        );
    }

    @Override
    public RoleDto updateRole(RoleDto roleDto) {
        var role = roleMapper.toEntity(roleDto);

        role = roleRepository.save(role);

        return roleMapper.toDto(role);
    }

    @Override
    public Boolean deleteRole(Long roleId) {
        try {
            roleRepository.deleteById(roleId);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
