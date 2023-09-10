package dev.oleksa.sportshop.mapper;

import dev.oleksa.sportshop.exception.NotFoundException;
import dev.oleksa.sportshop.model.dto.UserDto;
import dev.oleksa.sportshop.model.user.Role;
import dev.oleksa.sportshop.model.user.UserEntity;
import dev.oleksa.sportshop.model.user.address.Address;
import dev.oleksa.sportshop.repository.AddressRepository;
import dev.oleksa.sportshop.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserMapper {

    private final ModelMapper mapper;
    private final RoleRepository roleRepository;
    private final AddressRepository addressRepository;

    public UserEntity toEntity(UserDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, UserEntity.class);
    }

    public UserDto toDto(UserEntity entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, UserDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(UserEntity.class, UserDto.class)
                .addMappings(m -> m.skip(UserDto::setRoleIds)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(UserDto::setAddressIds)).setPostConverter(toDtoConverter())
        ;
        mapper.createTypeMap(UserDto.class, UserEntity.class)
                .addMappings(m -> m.skip(UserEntity::setRoles)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(UserEntity::setAddresses)).setPostConverter(toEntityConverter())
        ;
    }

    public Converter<UserEntity, UserDto> toDtoConverter() {
        return context -> {
            UserEntity source = context.getSource();
            UserDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public Converter<UserDto, UserEntity> toEntityConverter() {
        return context -> {
            UserDto source = context.getSource();
            UserEntity destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public void mapSpecificFields(UserDto source, UserEntity destination) {
        try {
            destination.setRoles(
                    Objects.isNull(source) || Objects.isNull(source.getRoleIds())
                            ? null
                            : getRoles(source.getRoleIds())
            );
            destination.setAddresses(
                    Objects.isNull(source) || Objects.isNull(source.getAddressIds())
                            ? null
                            : getAddress(source.getAddressIds())
            );
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void mapSpecificFields(UserEntity source, UserDto destination) {
        destination.setRoleIds(
                Objects.isNull(source) || Objects.isNull(source.getRoles())
                        ? null
                        : getRoleIds(source.getRoles())
        );
        destination.setAddressIds(
                Objects.isNull(source) || Objects.isNull(source.getAddresses())
                        ? null
                        : getAddressIds(source.getAddresses())
        );
    }

    private List<Role> getRoles(List<Long> roleIds) throws NotFoundException {
        List<Role> roles = new ArrayList<>();
        for (Long id : roleIds) {
            roles.add(roleRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Role not found")));
        }
        return roles;
    }

    private Set<Address> getAddress(Set<Long> addressIds) throws NotFoundException {
        Set<Address> addresses = new HashSet<>();
        for (Long id : addressIds) {
            addresses.add(addressRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Address not found")));
        }
        return addresses;
    }

    private List<Long> getRoleIds(Collection<Role> roles) {
        List<Long> ids = new ArrayList<>();
        for (Role item : roles) {
            ids.add(item.getId());
        }
        return ids;
    }

    private Set<Long> getAddressIds(Set<Address> addresses) {
        Set<Long> ids = new HashSet<>();
        for (Address item : addresses) {
            ids.add(item.getId());
        }
        return ids;
    }
}
