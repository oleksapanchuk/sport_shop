package dev.oleksa.sportshop.mapper;

import dev.oleksa.sportshop.dto.RoleDto;
import dev.oleksa.sportshop.model.user.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class RoleMapper {

    private final ModelMapper mapper;

    public Role toEntity(RoleDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Role.class);
    }

    public RoleDto toDto(Role entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, RoleDto.class);
    }
}
