package causebankgrp.causebank.Servicelmpl;

import java.time.ZonedDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import causebankgrp.causebank.Dto.Response.AuthResponse;
import causebankgrp.causebank.Dto.Response.UserDTO;
import causebankgrp.causebank.Dto.request.LoginRequest;
import causebankgrp.causebank.Dto.request.SignupRequest;
import causebankgrp.causebank.Entity.User;
import causebankgrp.causebank.Exception.AuthException;
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

    @Override
    public AuthResponse signup(SignupRequest request) {
        try {
            // logger.info("Signup request: {}", request);
    
            if (userRepository.existsByEmail(request.getEmail())) {
                // logger.error("Email already exists: {}", request.getEmail());
                throw new AuthException("Email already exists");
            }
    
            User user = new User();
            user.setEmail(request.getEmail());
            String encodedPassword = passwordEncoder.encode(request.getPassword());

            logger.info(" password: {}", request.getPassword());
    
            // Log the encoded password to verify the encoding format
            logger.info("Encoded password: {}", encodedPassword);
    
            user.setPasswordHash(encodedPassword);
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setRole(request.getRole());
            user.setPhone(request.getPhone());
            user.setIsEmailVerified(false);
            user.setIsPhoneVerified(false);
            user.setIsActive(true);
    
            // logger.info("User created: {}", user);
    
            user = userRepository.save(user);
            String token = jwtUtil.generateToken(user);
    
            // logger.info("Generated token for user: {}", token);
    
            return AuthResponse.builder()
                .token(token)
                .user(mapToUserDTO(user))
                .build();
        } catch (AuthException ae) {
            logger.error("Authentication error during signup: {}", ae.getMessage(), ae);
            throw ae; // Re-throw the AuthException
        } catch (Exception e) {
            // logger.error("Unexpected error during signup: {}", e.getMessage(), e);
            throw new AuthException("An error occurred during signup "+ e.getMessage());
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
                .user(mapToUserDTO(user))
                .build();
        } catch (AuthException ae) {
            logger.error("Authentication error during login: {}", ae.getMessage(), ae);
            throw ae; // Re-throw the AuthException
        } catch (Exception e) {
            logger.error("Unexpected error during login: {}", e.getMessage(), e);
            throw new AuthException("An error occurred during login");
        }
    }

    @Override
    public void logout(String token) {
        // In a real application, you might want to blacklist the token
        // This would require a Redis or similar cache to store invalid tokens
        try {
            logger.info("Logout request for token: {}", token);
            // Your token invalidation logic (if needed)
        } catch (Exception e) {
            logger.error("Error during logout: {}", e.getMessage(), e);
            throw new AuthException("An error occurred during logout");
        }
    }

    private UserDTO mapToUserDTO(User user) {
        return UserDTO.builder()
            .id(user.getId().toString())
            .email(user.getEmail())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .role(user.getRole().name())
            .avatarUrl(user.getAvatarUrl())
            .phone(user.getPhone())
            .isEmailVerified(user.getIsEmailVerified())
            .isPhoneVerified(user.getIsPhoneVerified())
            .build();
    }
}
