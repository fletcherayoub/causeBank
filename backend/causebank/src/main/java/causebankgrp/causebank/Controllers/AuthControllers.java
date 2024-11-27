package causebankgrp.causebank.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import causebankgrp.causebank.Dto.Response.ApiResponse;
import causebankgrp.causebank.Dto.Response.AuthResponse;
import causebankgrp.causebank.Dto.request.LoginRequest;
import causebankgrp.causebank.Dto.request.SignupRequest;
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

        // Get the token from the AuthResponse
        String token = authResponse.getToken();

        // Create a new cookie to store the JWT token
        Cookie tokenCookie = new Cookie("JWT", token);
        tokenCookie.setHttpOnly(true); // Ensures that the cookie is not accessible via JavaScript
        tokenCookie.setSecure(true);   // Ensures that the cookie is only sent over HTTPS
        tokenCookie.setPath("/");      // Available for the entire app
        tokenCookie.setMaxAge(3600);  // Token expiration time (1 hour in this case)
        // tokenCookie.setSameSite("Strict"); // Prevents CSRF attacks

        // Add the cookie to the response
        response.addCookie(tokenCookie);

        // Return the response inside ApiResponse
        return ResponseEntity.ok(ApiResponse.success("Login successful", authResponse));
    } catch (Exception e) {
        return ResponseEntity.status(500).body(ApiResponse.error("Login failed"));
    }
}


    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestHeader("Authorization") String token) {
        authService.logout(token.replace("Bearer ", ""));
        return ResponseEntity.ok(ApiResponse.success("Logout successful", null));
    }
}