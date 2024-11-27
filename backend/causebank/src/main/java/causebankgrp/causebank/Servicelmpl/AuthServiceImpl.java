package causebankgrp.causebank.Servicelmpl;

import java.time.ZonedDateTime;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import causebankgrp.causebank.Dto.AuthDTO.Response.AuthResponse;
import causebankgrp.causebank.Dto.AuthDTO.request.LoginRequest;
import causebankgrp.causebank.Dto.AuthDTO.request.SignupRequest;
import causebankgrp.causebank.Entity.User;
import causebankgrp.causebank.Exception.Auth_Authorize.AuthException;
import causebankgrp.causebank.Helpers.AuthMapper;
import causebankgrp.causebank.Repository.UserRepository;
import causebankgrp.causebank.Security.JwtUtil;
import causebankgrp.causebank.Services.AuthService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthMapper authMapper;

    @Override
    public AuthResponse signup(SignupRequest request) {
        try {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new AuthException("Email already exists");
            }

            User user = new User();
            user.setEmail(request.getEmail());
            user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setRole(request.getRole());
            user.setPhone(request.getPhone());
            user.setIsEmailVerified(false);
            user.setIsPhoneVerified(false);
            user.setIsActive(true);

            user = userRepository.save(user);
            String token = jwtUtil.generateToken(user);

            return AuthResponse.builder()
                .token(token)
                .user(authMapper.toUserDTO(user))
                .build();
        } catch (AuthException ae) {
            logger.error("Authentication error during signup: {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            throw new AuthException("An error occurred during signup " + e.getMessage());
        }   
    }
    
    @Override
    public AuthResponse login(LoginRequest request) {
        try {
            User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthException("Invalid email or password"));

            if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
                throw new AuthException("Invalid email or password");
            }

            if (!user.getIsActive()) {
                throw new AuthException("Account is disabled");
            }

            String token = jwtUtil.generateToken(user);
            user.setLastLoginAt(ZonedDateTime.now());
            userRepository.save(user);

            return AuthResponse.builder()
                .token(token)
                .user(authMapper.toUserDTO(user))
                .build();
        } catch (AuthException ae) {
            logger.error("Authentication error during login: {}", ae.getMessage(), ae);
            throw ae;
        } catch (Exception e) {
            logger.error("Unexpected error during login: {}", e.getMessage(), e);
            throw new AuthException("An error occurred during login");
        }
    }

    @Override
    public void logout(String token) {
        try {
            logger.info("Logout request for token: {}", token);
            // Token invalidation logic can be added here if needed
        } catch (Exception e) {
            logger.error("Error during logout: {}", e.getMessage(), e);
            throw new AuthException("An error occurred during logout");
        }
    }
}