package com.movieapp.moviemanagement.service;

import com.movieapp.moviemanagement.entity.Role;
import com.movieapp.moviemanagement.entity.TokenEntity;
import com.movieapp.moviemanagement.entity.UserEntity;
import com.movieapp.moviemanagement.repository.TokenRepository;
import com.movieapp.moviemanagement.repository.UserRepository;
import com.movieapp.moviemanagement.request.LoginRequest;
import com.movieapp.moviemanagement.request.RegisterRequest;
import com.movieapp.moviemanagement.response.AuthResponse;
import com.movieapp.moviemanagement.security.JWTGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;
    private final TokenRepository tokenRepository;

    @Value("${jwt_expiration}")
    private Long expiration;

    public String register(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException( "User already exists");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userEntity.setRole(Role.USER);
        userRepository.save(userEntity);
        return "User registered successfully";
    }

    public AuthResponse login(LoginRequest loginRequest) {
       try {
           Authentication authentication = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           loginRequest.getUsername(),
                           loginRequest.getPassword()
                   )
           );
           SecurityContextHolder.getContext().setAuthentication(authentication);
           String token = jwtGenerator.generateToken(authentication);
           UserEntity user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
           tokenRepository.deleteByUser(user);
           TokenEntity tokenEntity = new TokenEntity();
           tokenEntity.setAccessToken(token);
           tokenEntity.setTokenExpiry(Instant.now().plusSeconds(expiration));
           tokenEntity.setUser(user);
           tokenRepository.save(tokenEntity);

           return new AuthResponse(token);
//           return "Login successful";
       }catch (Exception e) {
           throw new RuntimeException(e.getMessage());
       }
    }

    public void logout(String token) {
        String jwt = token.substring(7);

        TokenEntity tokenEntity = tokenRepository.findByAccessToken(jwt).orElseThrow();
        tokenRepository.delete(tokenEntity);
    }

}
