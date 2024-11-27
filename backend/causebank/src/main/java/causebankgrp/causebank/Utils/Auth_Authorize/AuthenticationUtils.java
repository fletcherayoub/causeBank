package causebankgrp.causebank.Utils.Auth_Authorize;

import causebankgrp.causebank.Entity.User;
import causebankgrp.causebank.Enums.UserRole;
import causebankgrp.causebank.Exception.Auth_Authorize.UnauthorizedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthenticationUtils {
    public User getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !(authentication.getPrincipal() instanceof User)) {
            throw new UnauthorizedException("User not authenticated");
        }
        
        return (User) authentication.getPrincipal();
    }

    public UUID getCurrentAuthenticatedUserId() {
        User user = getCurrentAuthenticatedUser();
        return user.getId();
    }

    public boolean isCurrentUserAdmin() {
    User user = getCurrentAuthenticatedUser();
    return user.getRole() == UserRole.ADMIN;
}

    public boolean isCurrentUserOwnResource(UUID resourceOwnerId) {
        return getCurrentAuthenticatedUserId().equals(resourceOwnerId);
    }
}