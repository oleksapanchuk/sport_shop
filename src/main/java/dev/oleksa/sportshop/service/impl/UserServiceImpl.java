package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.model.dto.UserDto;
import dev.oleksa.sportshop.model.user.Role;
import dev.oleksa.sportshop.model.user.UserEntity;
import dev.oleksa.sportshop.model.user.address.Address;
import dev.oleksa.sportshop.repository.RoleRepository;
import dev.oleksa.sportshop.repository.UserRepository;
import dev.oleksa.sportshop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = repository.findByEmail(email).orElseThrow();
        log.info("User found! {}", email);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public UserEntity getUserById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return repository.findByEmail(email).orElseThrow();
    }

    @Override
    public UserEntity createUser(UserDto user) {
        log.info("Creating and saving new user {} to the database", user.getFirstName() + " " + user.getLastName());

        return repository.save(UserEntity.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roles(List.of(roleRepository.findByName(user.getRoleName())))
                .phone(user.getPhone())
                .dateOfBirth(user.getDateOfBirth())
                .build()
        );
    }

    @Override
    public UserEntity updateUser(UserEntity user) {

        return null;
    }

    @Override
    public UserEntity addRoleToUser(UserEntity user, Role role) {
        user.getRoles().add(role);
        return updateUser(user);
    }

    @Override
    public UserEntity removeRoleFromUser(UserEntity user, Role role) {
        return null;
    }

    @Override
    public UserEntity addAvatar(UserEntity user, String filePath) {
        return null;
    }

    @Override
    public UserEntity removeAvatar(UserEntity user) {
        return null;
    }

    @Override
    public UserEntity addAddressToUser(UserEntity user, Address address) {
        return null;
    }

    @Override
    public UserEntity removeAddress(UserEntity user, Address address) {
        return null;
    }

    @Override
    public List<UserEntity> getUsers() {
        return null;
    }

}
