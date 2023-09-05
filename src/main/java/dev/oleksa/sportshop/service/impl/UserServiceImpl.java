package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.model.dto.UserCreationDto;
import dev.oleksa.sportshop.model.user.Role;
import dev.oleksa.sportshop.model.user.UserEntity;
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
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = repository.findByEmail(email).orElseThrow();
        if (user == null) {
            log.error("User not found!");
            // todo
            throw new UsernameNotFoundException("User not found in the database");
        }
        else {
            log.info("User found! {}", email);
        }

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
    public UserEntity saveUser(UserCreationDto user) {
        log.info("Saving new user {} to the database", user.getFirstName() + " " + user.getLastName());
        return repository.save(UserEntity.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .roles(new ArrayList<>(){}) // todo
                .email(user.getEmail())
                .password(user.getPassword()) // todo password encryptor
                .phone(user.getPhone())
                .dateOfBirth(user.getDateOfBirth())
                .build()
        );
    }

    @Override
    public Role addRoleToUser(Role role) {
        return null;
    }

    @Override
    public UserEntity getUser(String email) {
        return null;
    }

    @Override
    public List<UserEntity> getUsers() {
        return null;
    }

}
