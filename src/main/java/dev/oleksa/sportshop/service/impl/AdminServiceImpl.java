package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.dto.UserDto;
import dev.oleksa.sportshop.mapper.UserMapper;
import dev.oleksa.sportshop.model.user.Role;
import dev.oleksa.sportshop.model.user.UserEntity;
import dev.oleksa.sportshop.repository.RoleRepository;
import dev.oleksa.sportshop.repository.UserRepository;
import dev.oleksa.sportshop.security.auth.RegisterRequest;
import dev.oleksa.sportshop.service.AdminService;
import dev.oleksa.sportshop.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(RegisterRequest request) {
        Long ROLE_CUSTOMER_ID = 1L;

        log.info("Creating and saving new user {} to the database", request.getFirstName() + " " + request.getLastName());

        List<Role> roles = List.of(
                roleRepository.findById(ROLE_CUSTOMER_ID)
                        .orElseThrow()
        );

        UserEntity newUser;
        try {
            newUser = UserEntity.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .roles(roles)
                    .phone(request.getPhone())
                    .dateOfBirth(DataUtils.getDate(request.getDateOfBirth()))
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        newUser = userRepository.save(newUser);

        return userMapper.toDto(newUser);
    }

    @Override
    public Boolean addRoleToUser(Long userId, Long roleId) {
        return null;
    }

    @Override
    public Boolean removeRoleFromUser(Long userId, Long roleId) {
        return null;
    }
}
