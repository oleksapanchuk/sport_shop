package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.dto.UserDto;
import dev.oleksa.sportshop.mapper.UserMapper;
import dev.oleksa.sportshop.model.user.UserEntity;
import dev.oleksa.sportshop.repository.UserRepository;
import dev.oleksa.sportshop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = repository.findByEmail(email).orElseThrow();
        log.info("User found! {}", email);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public UserDto getUserById(Long id) {
        return userMapper.toDto(
                repository.findById(id)
                        .orElseThrow()
        );
    }

    @Override
    public Boolean updateUser(Long userId, UserDto userDto) {

        int countRows = repository.updateUserInfo(
                userId,
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getPhone(),
                userDto.getDateOfBirth()
        );

        return countRows > 0;
    }

    @Override
    public Boolean updateAvatar(Long userId, String imageUrl) {

        int countRows = repository.updateUserAvatar(userId, imageUrl);

        return countRows > 0;
    }

    @Override
    public Boolean deleteAvatar(Long userId) {
        String NO_AVATAR = "https://vyshnevyi-partners.com/wp-content/uploads/2016/12/no-avatar.png";

        int countRows = repository.updateUserAvatar(userId, NO_AVATAR);

        return countRows > 0;
    }

    @Override
    public Boolean updateGender(Long userId, Long genderId) {

        log.info("User id: " + userId + " Gender id: " + genderId);

        int countRows = repository.updateUserGender(userId, genderId);

        return countRows > 0;
    }

    @Override
    public Boolean updatePassword(Long userId, String oldPassword, String newPassword) {
        return null;
    }

}
