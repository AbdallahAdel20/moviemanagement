package com.movieapp.moviemanagement.controller;

import com.movieapp.moviemanagement.request.LoginRequest;
import com.movieapp.moviemanagement.request.RegisterRequest;
import com.movieapp.moviemanagement.response.ApiResponse;
import com.movieapp.moviemanagement.response.AuthResponse;
import com.movieapp.moviemanagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(new ApiResponse<>(true, authService.register(registerRequest), "User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(@RequestHeader("Authorization")  String token) {
        authService.logout(token);
        return ResponseEntity.ok(new ApiResponse<>(true, null, "User logged out successfully"));
    }
}
