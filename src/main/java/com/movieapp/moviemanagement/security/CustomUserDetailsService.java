package com.movieapp.moviemanagement.security;

import com.movieapp.moviemanagement.entity.Role;
import com.movieapp.moviemanagement.entity.UserEntity;
import com.movieapp.moviemanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.singleton(getAuthority(userEntity.getRole())));
    }

    private GrantedAuthority getAuthority(Role role) {
        return () -> role.name();
    }
}
