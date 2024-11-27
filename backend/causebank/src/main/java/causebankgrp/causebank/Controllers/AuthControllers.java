package causebankgrp.causebank.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import causebankgrp.causebank.Dto.AuthDTO.Response.ApiResponse;
import causebankgrp.causebank.Dto.AuthDTO.Response.AuthResponse;
import causebankgrp.causebank.Dto.AuthDTO.request.LoginRequest;
import causebankgrp.causebank.Dto.AuthDTO.request.SignupRequest;
import causebankgrp.causebank.Security.JwtUtil;
import causebankgrp.causebank.Services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

// where we will handle the api

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthControllers {
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<AuthResponse>> signup(@Valid @RequestBody SignupRequest request) {
        return ResponseEntity.ok(ApiResponse.success(
            "User registered successfully",
            authService.signup(request)
        ));
    }

  @PostMapping("/login")
public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request, HttpServletResponse response) {
    try {
        // Authenticate the user and get the response with the token
        AuthResponse authResponse = authService.login(request);

        Cookie tokenCookie = jwtUtil.createTokenCookie(authResponse.getToken()); 
        // Add the cookie to the response
        response.addCookie(tokenCookie);

        // Return the response inside ApiResponse
        return ResponseEntity.ok(ApiResponse.success("Login successful", authResponse));
    } catch (Exception e) {
        return ResponseEntity.status(500).body(ApiResponse.error("Login failed"));
    }
}


@PostMapping("/logout")
public ResponseEntity<ApiResponse<Void>> logout(HttpServletResponse response) {
    Cookie logoutCookie = jwtUtil.createLogoutCookie();
    response.addCookie(logoutCookie);
    return ResponseEntity.ok(ApiResponse.success("Logout successful", null));
}
}