package dev.oleksa.sportshop.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.oleksa.sportshop.model.user.Gender;
import dev.oleksa.sportshop.model.user.Role;
import dev.oleksa.sportshop.model.user.UserEntity;
import dev.oleksa.sportshop.repository.GenderRepository;
import dev.oleksa.sportshop.repository.RoleRepository;
import dev.oleksa.sportshop.repository.UserRepository;
import dev.oleksa.sportshop.security.JwtService;
import dev.oleksa.sportshop.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    public static final String ROLE_CUSTOMER = "ROLE_CUSTOMER";
    /*
        public static final String ROLE_MANAGER = "ROLE_MANAGER";
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
    */
    public static final String BEARER = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final GenderRepository genderRepository;

    public AuthenticationResponse register(RegisterRequest request) {

        UserEntity user;

        String password = passwordEncoder.encode(request.getPassword());
        log.info("Password encoded");

        List<Role> roles = List.of(
                roleRepository.findByName(ROLE_CUSTOMER)
        );

        Gender gender = genderRepository.findById(request.getGenderId())
                .orElseThrow();

        try {
            user = UserEntity.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(password)
                    .roles(roles)
                    .phone(request.getPhone())
                    .gender(gender)
                    .dateOfBirth(DataUtils.getDate(request.getDateOfBirth()))
                    .isConfirmed(false)
                    .isBlocked(false)
                    .isSubscribed(false)
                    .createdAt(LocalDateTime.now())
                    .modifiedAt(LocalDateTime.now())
                    .build();
        } catch (ParseException e) {
            return AuthenticationResponse.builder()
                    .error(e.getMessage())
                    .build();
        }

        repository.save(user);
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authHeader == null || !authHeader.startsWith(BEARER)) {
            log.info("Authorization header is empty or it has not bearer token");
            throw new AccessDeniedException("Access denied!");
        }

        final String refreshToken = authHeader.substring(BEARER.length());
        final String userEmail = jwtService.extractEmail(refreshToken);
        final String accessToken;

        try {
            // get user details from the database
            UserDetails user = this.userDetailsService.loadUserByUsername(userEmail);

            if (userEmail == null) {
                throw new RuntimeException();
            }

            accessToken = jwtService.generateToken(user);

            Map<String, String> tokens = new HashMap<>();
            tokens.put("access_token", accessToken);
            tokens.put("refresh_token", refreshToken);
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), tokens);

        } catch (Exception e) {
            response.setHeader("error", e.getMessage());
            response.setStatus(FORBIDDEN.value());
            Map<String, String> error = new HashMap<>();
            error.put("error_message", e.getMessage());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);

        }
    }
}
