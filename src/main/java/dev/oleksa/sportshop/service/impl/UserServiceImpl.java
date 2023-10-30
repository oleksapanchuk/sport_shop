package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.dto.UserDto;
import dev.oleksa.sportshop.mapper.UserMapper;
import dev.oleksa.sportshop.model.user.UserEntity;
import dev.oleksa.sportshop.repository.UserRepository;
import dev.oleksa.sportshop.security.JwtService;
import dev.oleksa.sportshop.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Key;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private static final String SECRET_KEY = "ad1c7f4d0f1fb2e0d07f2eba294ba1746ebac64119ba983caab9b9a2a15f1682";
    private static final Integer EXPIRATION_TIME = 1000 * 60 * 24;

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
    public Boolean confirmAccount(Long userId) {
        UserEntity user = repository.findById(userId)
                .orElseThrow();

        // send confirmation email

        return null;
    }

    @Override
    public String generateConfirmationToken(String email) {

        return Jwts
                .builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public Boolean verifyConfirmationToken(String token) {

        if (new JwtService().isTokenExpired(token)) {
            return false;
        }

        String email = new JwtService().extractEmail(token);

        UserEntity user = repository.findByEmail(email)
                .orElseThrow();



        return null;
    }

    @Override
    public Boolean updatePassword(Long userId, String oldPassword, String newPassword) {
        return null;
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
