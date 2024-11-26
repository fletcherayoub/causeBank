package causebankgrp.causebank.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import causebankgrp.causebank.Dto.Response.ApiResponse;
import causebankgrp.causebank.Dto.Response.AuthResponse;
import causebankgrp.causebank.Dto.request.LoginRequest;
import causebankgrp.causebank.Dto.request.SignupRequest;
import causebankgrp.causebank.Services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

// where we will handle the api

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthControllers {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<AuthResponse>> signup(@Valid @RequestBody SignupRequest request) {
        return ResponseEntity.ok(ApiResponse.success(
            "User registered successfully",
            authService.signup(request)
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(ApiResponse.success(
            "Login successful",
            authService.login(request)
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestHeader("Authorization") String token) {
        authService.logout(token.replace("Bearer ", ""));
        return ResponseEntity.ok(ApiResponse.success("Logout successful", null));
    }
}