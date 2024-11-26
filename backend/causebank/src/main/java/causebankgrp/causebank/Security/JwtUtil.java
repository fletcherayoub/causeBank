package causebankgrp.causebank.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import causebankgrp.causebank.Entity.User;

@Service
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public String generateToken(User user) {
        logger.info("Generating token for user: {}", user.getEmail());
        
        SecretKey key = getSignInKey();
        
        String token = Jwts.builder()
            .subject(user.getEmail())
            .claim("role", user.getRole().name())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
            .signWith(key)
            .compact();

        logger.info("Generated token for {}: {}", user.getEmail(), token);
        return token;
    }

    private SecretKey getSignInKey() {
        // Decode the Base64 encoded secret key and create a secure key
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        
        // Use Keys.hmacShaKeyFor for more secure key generation
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractEmail(String token) {
        return Jwts.parser()
            .verifyWith(getSignInKey())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }

    public boolean validateToken(String jwt, User user) {
        try {
            String emailFromToken = extractEmail(jwt);
            boolean isValid = emailFromToken.equals(user.getEmail());
            logger.info("Token validation for user: {}. Is valid: {}", user.getEmail(), isValid);
            return isValid;
        } catch (Exception e) {
            logger.error("Token validation failed", e);
            return false;
        }
    }
}